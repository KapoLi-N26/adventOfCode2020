package Day13Part01

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    var busSchedule = getResourceText("bus.txt").trim()
        .split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .toList()

    val arrivalTime = busSchedule[0].toLong()
    val busDeparts = busSchedule[1]
        .split(",")
        .filter { it != "x" }
        .map { it.toLong() }
        .toList()

    val earliestDeparture = busDeparts
        .stream()
        .map {
            val mod = arrivalTime % it
            if (mod == 0L) {
                arrivalTime to it
            } else {
                (arrivalTime - mod) + it to it
            }
        }
        .toList()
        .sortedWith(compareBy { it.first })
        .first()

    val waitTime = earliestDeparture.first - arrivalTime;
    val busNumber = earliestDeparture.second
    println(waitTime * busNumber)
}

