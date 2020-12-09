package Day08Part02

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    val instructions = getResourceText("game.txt")
        .split("\n")
        .filter { it.isNotEmpty() }
        .stream()
        .map { it.split(" ") }
        .map { it[0] to it[1].toInt() }
        .toList()

    for(i in instructions.indices) {
        val result = findAccumulator(instructions, i)
        if(result.second) {
            println(result)
            break
        }
    }
}
fun findAccumulator(instructions: List<Pair<String, Int>>, changeIndex: Int): Pair<Int, Boolean> {
    if(instructions[changeIndex].first == "acc") {
        return 0 to false
    }

    var accumulator = 0
    val stepsTaken = mutableSetOf<Int>()

    var atStep = 0
    while(!stepsTaken.contains(atStep) && atStep < instructions.size) {
        stepsTaken.add(atStep)
        val command = if(atStep == changeIndex) {
            if(instructions[atStep].first == "jmp") {
                "nop"
            } else {
                "jmp"
            }
        } else {
            instructions[atStep].first
        }
        when(command) {
            "nop" -> atStep++
            "acc" -> {
                accumulator += instructions[atStep].second
                atStep++
            }
            "jmp" -> atStep += instructions[atStep].second
        }
    }

    return accumulator to (atStep==instructions.size)
}
