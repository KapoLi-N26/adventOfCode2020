package Day16Part02

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    var ticketing = getResourceText("trainTicket.txt")
        .trim()
        .split("\n\n")
        .stream()
        .filter { it.isNotEmpty() }
        .toList()

    val rules = Rules(ticketing[0])

    val nearbyTickets = ticketing[2]
        .split("\n")
        .filter { it.isNotEmpty() }
        .filter { it != "nearby tickets:" }
        .map { it.trim().split(",").map { v -> v.toLong() }.toList() }
        .map { Ticket(it) }
        .toList()

    val fields = nearbyTickets.map { it.getNumOfFields() }.maxOrNull() ?: 0

    val posToFiledNamePerTicket = nearbyTickets.map { ticket ->
        ticket.getPositionsToPossibleFieldsMap(rules)
    }.filter { !it.isNullOrEmpty() }

    var fieldNameToPos = mutableMapOf<String, Int>()
    var unresolved = mutableMapOf<Int, MutableSet<String>>()
    for(i in 0 until fields) {
        var outcome = posToFiledNamePerTicket.stream()
            .map { it?.get(i) }
            .filter { !it.isNullOrEmpty() }
            .toList()
            .reduce { acc, set -> acc.orEmpty() intersect set.orEmpty() }

        if(outcome?.size == 1) {
            fieldNameToPos[outcome.first()] = i
        } else {
            unresolved[i] = outcome.orEmpty().toMutableSet()
        }
    }

    while(unresolved.isNotEmpty()) {
        unresolved.forEach { (pos, fields) ->
            fields.removeIf { fieldNameToPos.containsKey(it) }
        }
        unresolved.filter { it.value.size == 1 }
            .map {
                fieldNameToPos[it.value.first()] = it.key
                it.key
            }.toList()
            .forEach { unresolved.remove(it) }
    }

    val myTicket = ticketing[1].split("\n")
        .filter { it.isNotEmpty() }
        .filter { it != "your ticket:" }
        .map { it.trim().split(",").map { v -> v.toLong() } }
        .first()

    println(fieldNameToPos.filterKeys { it.startsWith("departure") }
        .map { myTicket[it.value] }
        .fold(1L) {product, value -> product * value})
}

class Rules(input: String) {

    val rules = mutableMapOf<String, Set<LongRange>>()

    init {
        input.trim().split("\n").stream()
            .map {
                val parts = it.split(": ")
                var twoRanges = parts[1].split(" or ").map { range ->
                    val r = range.split("-")
                    r[0].toLong()..r[1].toLong()
                }.toSet()
                rules.put(parts[0], twoRanges)
            }.toList()

    }
}

class Ticket(val values: List<Long>) {

    fun getNumOfFields() = values.size

    fun getPositionsToPossibleFieldsMap(ruleSet: Rules): MutableMap<Int, Set<String>>? {
        val map = mutableMapOf<Int, Set<String>>()
        for (i in values.indices) {
            val qualifies = ruleSet.rules
                .filter { entry -> entry.value.any { values[i] in it } }
                .map { entry -> entry.key }
                .toSet()
            if (qualifies.isEmpty()) {
                break
            }
            map[i] = qualifies
        }
        return if (map.keys.size == values.size) {
            map
        } else {
            null
        }
    }
}
