package Day14Part02

import utils.getResourceText
import java.math.BigInteger
import java.util.LinkedList
import java.util.Queue
import java.util.stream.Collectors
import kotlin.streams.toList

fun main(args: Array<String>) {
    var masks = getResourceText("mask.txt").trim()
        .split("\n")
        .stream()
        .filter { it.isNotEmpty() }
        .toList()

    val memoryLocToValue = mutableMapOf<BigInteger, BigInteger>()
    var currMask = Mask(masks[0])
    for (i in 1..masks.lastIndex) {
        if (masks[i].startsWith("mem")) {
            val mem = Mem(masks[i])
            mem.permutate(currMask)?.forEach {
                memoryLocToValue.put(it, mem.value)
            }
        } else {
            currMask = Mask(masks[i])
        }
    }

    println(memoryLocToValue.values.fold(BigInteger.ZERO) { sum, v -> sum + v })
}

class Mask(input: String) {

    val value: String = input.split(" = ")[1]
}

class Mem(input: String) {

    val memoryLocation: String
    var value: BigInteger

    init {
        val parts = input.split(" = ")
        memoryLocation = parts[0]
            .substringAfter("[")
            .substringBefore("]")
            .toInt().toString(2)
            .padStart(length = 36, padChar = '0')
        value = parts[1].toBigInteger()
    }

    fun permutate(mask: Mask): MutableSet<BigInteger>? {
        val queue = LinkedList<String>()
        var result = ""
        for (i in 0..35) {
            if (mask.value[i] == 'X') {
                result += 'X'
            } else if (mask.value[i] == '1' || memoryLocation[i] == '1') {
                result += '1'
            } else {
                result += '0'
            }
        }
        queue.add(result)
        var currQ = 1;
        var nextQ = 0;
        for(i in result.indices) {
            if(result[i] == 'X') {
                for(j in 0 until currQ) {
                    var temp = queue.remove().toCharArray()
                    temp[i] = '0'
                    queue.add(String(temp))
                    nextQ++
                    temp[i] = '1'
                    queue.add(String(temp))
                    nextQ++
                }
                currQ = nextQ
                nextQ = 0
            }
        }
        return queue.stream().map { it.toBigInteger(2) }.collect(Collectors.toSet())
    }
}
