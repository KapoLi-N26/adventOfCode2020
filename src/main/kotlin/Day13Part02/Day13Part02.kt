package Day13Part01

import utils.getResourceText
import java.math.BigInteger
import kotlin.streams.toList

fun main(args: Array<String>) {
    var busSchedule = getResourceText("busTest.txt").trim()
        .split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .toList()

    val busIdToTimeDelta = busSchedule[1]
        .split(",")
        .mapIndexed { index, s -> s to index }
        .filter { it.first != "x" }
        .map {
            val c = it.first.toBigInteger()
            c to it.second
        }
        .toList()

    var lcm = busIdToTimeDelta[0].first
    var time = busIdToTimeDelta[0].first
    var i = 1
    while (true) {
        val busId = busIdToTimeDelta[i].first
        val timeDelta = busIdToTimeDelta[i].second.toBigInteger()
        if ((time + timeDelta) % busId == BigInteger.ZERO) {
            if (i == busIdToTimeDelta.lastIndex) {
                println(time)
                return
            }
            i++
            lcm *= busId
            continue
        }
        time += lcm
    }

    println(time)
}
