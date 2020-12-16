package Day14Part01

import utils.getResourceText
import java.math.BigInteger
import kotlin.streams.toList

fun main(args: Array<String>) {
    var masks = getResourceText("mask.txt").trim()
        .split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .toList()

    val memoryLocToValue = mutableMapOf<Int, BigInteger>()
    var currMask = Mask(masks[0])
    for (i in 1..masks.lastIndex) {
        if (masks[i].startsWith("mem")) {
            val mem = Mem(masks[i])
            memoryLocToValue.put(mem.memoryLocation, mem.conform(currMask))
        } else {
            currMask = Mask(masks[i])
        }
    }

    println(memoryLocToValue.values.fold(BigInteger.ZERO) {sum, v -> sum + v})
}

class Mask(input: String) {

    val value: String = input.split(" = ")[1]
}

class Mem(input: String) {

    val memoryLocation: Int
    var value: String

    init {
        val parts = input.split(" = ")
        memoryLocation = parts[0].substringAfter("[").substringBefore("]").toInt()
        value = parts[1].toInt().toString(2).padStart(length = 36, padChar = '0')
    }

    fun conform(mask: Mask): BigInteger {
        var result = ""
        for (i in 0..35) {
            if (mask.value[i] == 'X') {
                result += value[i]
            } else {
                result += mask.value[i]
            }
        }
        return BigInteger(result, 2)
    }
}
