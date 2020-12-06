package Day06Part02

import utils.getResourceText
import kotlin.streams.toList

fun main(args: Array<String>) {
    val groups = getResourceText("customs.txt").split("\n\n").filter{it.isNotEmpty()}

    var sum = 0
    groups.forEach{ group ->
        val splitted = group.split("\n").filter { it.isNotEmpty() }

        val initialSet = splitted[0].toSet().toMutableSet()
        sum += splitted
            .stream()
            .map { it.toCharArray().toSet() }
            .toList()
            .fold(initialSet) {
                finalCharAry, ary -> finalCharAry.intersect(ary).toMutableSet()
            }.size
    }

    println(sum)
}

