package Day16Part01

import utils.getResourceText
import java.math.BigInteger
import kotlin.streams.toList

fun main(args: Array<String>) {
    var masks = getResourceText("mask.txt").trim()
        .split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .toList()

}
