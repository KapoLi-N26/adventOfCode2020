package Day10Part01

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

    var currentJolt = 0;
    var differences = arrayOf(0,0,1)
    for(i in adapters.indices) {
        val idx = (adapters[i] - currentJolt) - 1
        differences[idx]++
        currentJolt = adapters[i]
    }

    println(differences[0] * differences[2])
}
