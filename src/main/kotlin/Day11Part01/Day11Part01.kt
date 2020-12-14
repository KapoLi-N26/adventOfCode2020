package Day11Part01

import utils.getResourceText
import java.util.Arrays
import kotlin.streams.toList

val moves = arrayOf(
    arrayOf(-1,-1,-1,0,0,1,1,1),
    arrayOf(-1,0,1,-1,1,-1,0,1)
)
fun main(args: Array<String>) {
    var ferrySeats = getResourceText("ferry.txt").trim()
        .split("\n")
        .stream()
        .filter{it.isNotEmpty()}
        .toList()

    val ROWS = ferrySeats.size
    val COLS = ferrySeats[0].length
    var seatingChart = ferrySeats.toList()
    while(true) {
        var nextSeatingChart = getNewSeatingChart(ROWS)
        for(i in 0 until ROWS) {
            for(j in 0 until COLS) {
                nextSeatingChart[i] += predictSeatChange(seatingChart, i, j)
            }
        }
        if(nextSeatingChart != seatingChart) {
            seatingChart = nextSeatingChart
        } else {
            break
        }
    }

    println(seatingChart
        .stream()
        .map { it.count { it == '#' } }
        .toList()
        .fold(0) {sum, s -> sum + s})
}

fun predictSeatChange(ferrySeats: List<String>, row: Int, col: Int): String {
    val currentSeat = getSeat(ferrySeats, row, col)
    if(currentSeat == ".")
        return "."

    val adjacents = (0..7)
        .map { getSeat(ferrySeats, moves[0][it] + row, moves[1][it] + col) }
        .filter{ it.isNotEmpty() }
        .toList()

    val occupied = adjacents.filter { it == "#" }.count()
    return if(currentSeat == "L") {
        if (occupied == 0) {
            "#"
        } else {
            "L"
        }
    }
    else {
        if(occupied >= 4) {
            "L"
        } else {
            "#"
        }
    }
}

fun getSeat(ferrySeats: List<String>, row: Int, col: Int): String {
    if( row < 0 ||
        row >= ferrySeats.size ||
        col < 0 ||
        col >= ferrySeats[0].length
    ) {
        return ""
    }
    return ferrySeats[row][col].toString()
}

fun getNewSeatingChart(rows: Int): MutableList<String> {
    val list = mutableListOf<String>()
    for(i in 0 until rows)
        list.add("")
    return list
}
