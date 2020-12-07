package Day07Part01

import utils.getResourceText
import kotlin.streams.toList

val VISITED =  mutableSetOf<String>()
fun main(args: Array<String>) {
    val bagRules = getResourceText("bags.txt").split("\n").filter{it.isNotEmpty()}

    val rulesMap = mutableMapOf<String, MutableSet<String>>()
    bagRules.forEach { container ->
        val bagToContents = container.split(" bags contain ")
        val contents = bagToContents[1]
            .replace(".", "")
            .replace(" bags", "")
            .replace(" bag", "")
            .replace("[0-9]".toRegex(), "")
            .split(",")
        contents.forEach { bag ->
            if (bag.trim() != "no other") {
                val outer: MutableSet<String> = rulesMap.getOrDefault(bag.trim(), mutableSetOf())
                outer.add(bagToContents[0].trim())
                rulesMap[bag.trim()] = outer
            }
        }
    }

    numOuterBags("shiny gold", rulesMap)
    println(VISITED.size-1)
}

fun numOuterBags(
    lookForBag: String,
    rulesMap: MutableMap<String,
        MutableSet<String>>)
{
    VISITED.add(lookForBag)
    val containers = rulesMap[lookForBag]
    containers
        ?.filter { !VISITED.contains(it) }
        ?.forEach {
            numOuterBags(it, rulesMap)
        }
}

