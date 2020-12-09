package Day08Part01

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

    var accumulator = 0
    val stepsTaken = mutableSetOf<Int>()

    var atStep = 0
    while(!stepsTaken.contains(atStep)) {
        stepsTaken.add(atStep)
        when(instructions[atStep].first) {
            "nop" -> atStep++
            "acc" -> {
                accumulator += instructions[atStep].second
                atStep++
            }
            "jmp" -> atStep += instructions[atStep].second
        }
    }

    println(accumulator)
}
