package Day10Part02

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    val adapters = getResourceText("jolts.txt").trim()
        .split("\n")
        .stream()
        .filter{it.isNotEmpty()}
        .map { it.toInt() }
        .sorted()
        .toList()
        .toMutableList()
    adapters.add(adapters.last() + 3)
    adapters.add(0, 0)

    var currentJolt = 0;
    val dp = arrayOfNulls<Long>(size = adapters.size)
    dp[0] = 1
    dp.fill(0, 1, adapters.lastIndex)
    for(i in 1 until adapters.size) {
        val minIdx = 0.coerceAtLeast(i - 3)
        dp[i] = dp
            .filterIndexed { index, _ -> (index >= minIdx) && (index < i) && (adapters[i] - adapters[index]) in 1..3 }
            .fold(0L) {sum, value -> sum + value!!}
    }

    dp.forEach {
        println(it)
    }
}
