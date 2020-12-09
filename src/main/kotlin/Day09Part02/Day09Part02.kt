package Day09Part02

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    val target = 466456641L;

    val data = getResourceText("data.txt").trim()
        .split("\n")
        .stream()
        .map { it.toLong() }
        .toList()

    var startIdx = 0
    var endIdx = 1
    var sum = data[startIdx] + data[endIdx]

    while(sum != target) {
        if (sum < target) {
            endIdx++
            sum += data[endIdx]
        } else if ( sum > target) {
            sum -= data[startIdx]
            startIdx++
        }
    }

    val sorted = data.subList(startIdx, endIdx).sorted()
    println(sorted.first() + sorted.last())
}
