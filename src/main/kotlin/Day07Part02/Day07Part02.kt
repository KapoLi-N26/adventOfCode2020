package Day07Part02

import utils.getResourceText

fun main(args: Array<String>) {
    val bagRules = getResourceText("bags.txt").split("\n").filter{it.isNotEmpty()}

    val rulesMap = mutableMapOf<String, MutableSet<Pair<Int, String>>>()
    bagRules.forEach { container ->
        val bagToContents = container.split(" bags contain ")
        val contents = bagToContents[1]
            .replace(".", "")
            .replace(" bags", "")
            .replace(" bag", "")
            .split(",")
        val container = bagToContents[0].trim()
        contents.forEach{ numToBagColor ->
            if(numToBagColor != "no other") {
                val idx = numToBagColor.trim().indexOfFirst{ it == ' '}
                val num = numToBagColor.trim().substring(0, idx).toInt()
                val bag = numToBagColor.trim().substring(idx+1).trim()

                val set = rulesMap.getOrDefault(container, mutableSetOf())
                set.add(num to bag)
                rulesMap[container] = set
            }
        }
    }

    println(numBags("shiny gold", rulesMap)-1)
}

fun numBags(
    lookForBag: String,
    rulesMap: MutableMap<String, MutableSet<Pair<Int, String>>>
): Int {
    val contents = rulesMap[lookForBag]

    return 1 + (contents
        ?.fold(0) { sum, pair ->
            if(rulesMap.containsKey(pair.second)){
                println("${pair.first} x numBags(${pair.second})")
                sum + (pair.first * numBags(pair.second, rulesMap))
            }
            else {
                println("sum ($sum) + ${pair.first} ${pair.second} bags")
                sum + pair.first
            }
        } ?: 0)
}

