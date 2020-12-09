package Day09Part01

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    val preamble = 25;
    val data = getResourceText("data.txt").trim()
        .split("\n")
        .stream()
        .map { it.toLong() }
        .toList()

    for(i in preamble until data.size) {
        if(twoSum(i - preamble, preamble, data, data[i])) {
            println(data[i])
            break
        }
    }
}

fun twoSum(startIdx: Int, preamble: Int, list: List<Long>, sum: Long): Boolean {
    val seen = mutableSetOf<Long>()
    val endIdx = startIdx+(preamble-1)
    for (i in startIdx..endIdx) {
        val need = sum - list[i];
        if (seen.contains(need)) {
            return false
        } else {
            seen.add(list[i])
        }
    }
    return true
}
