package Day05Part01

import utils.getResourceText

fun main(args: Array<String>) {
    val maxSeatId = getResourceText("seats.txt")
        .split("\n")
        .filter { it.isNotEmpty() }
        .map { Seat.from(it) }
        .map { it.getSeatId() }
        .maxOrNull()

    println(maxSeatId)
}

