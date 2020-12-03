package Day01Part02

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    val list = getResourceText("input.txt").split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .map { it.toInt() }
        .toList()

    for(i in 0 until list.size - 2) {
        val remainder = 2020 - list[i]
        val twoSumOutput = twoSum(i+1, list, remainder)
        if(twoSumOutput != -1) {
            println("found " + list[i] * twoSumOutput)
        }
    }
}

fun twoSum(startIdx: Int, list: List<Int>, sum: Int): Int {
    val seen = mutableSetOf<Int>()
    for (i in startIdx until list.size) {
        val need = sum - list[i];
        if (seen.contains(need)) {
            return list[i] * need
        } else {
            seen.add(list[i])
        }
    }
    return -1
}

