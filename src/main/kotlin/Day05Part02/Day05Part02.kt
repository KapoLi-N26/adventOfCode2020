package Day05Part02

import utils.getResourceText

fun main(args: Array<String>) {
    val seatIds = getResourceText("seats.txt")
        .split("\n")
        .filter { it.isNotEmpty() }
        .map { Seat.from(it) }
        .map { it.getSeatId() }
        .sorted()

    for(i in 1 until seatIds.size) {
        if((seatIds[i] - seatIds[i-1]) == 2) {
            println(seatIds[i]-1)
        }
    }
}

