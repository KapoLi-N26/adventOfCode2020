package Day06Part01

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    val groups = getResourceText("customs.txt").split("\n\n").filter{it.isNotEmpty()}

    val sum = groups
        .stream()
        .map{it.replace("\n", "")}
        .map{it.toSet()}
        .map{it.size}
        .toList()
        .fold(0) {sum, s -> sum + s}

    println(sum)
}

