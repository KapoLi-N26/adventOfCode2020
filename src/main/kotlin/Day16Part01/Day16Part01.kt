package Day16Part01

import utils.getResourceText
import java.math.BigInteger
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
        .toList()

    var sum = 0L
    nearbyTickets.forEach { ticket ->
        for (l in ticket) {
            if (!rules.applies(l)) {
                sum += l
            }
        }
    }

    print(sum)
}

class Rules(input: String) {

    val rules = mutableSetOf<LongRange>()

    init {
        val next = input.trim().split("\n").stream()
            .map {
                it.split(": ")[1].split(" or ")
            }.toList()
        next.forEach {
            for (s in it) {
                val parts = s.split("-")
                rules.add(parts[0].toLong()..parts[1].toLong())
            }
        }
    }

    fun applies(value: Long): Boolean = rules.any { value in it }
}
