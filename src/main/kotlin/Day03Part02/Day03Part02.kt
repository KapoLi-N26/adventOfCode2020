package Day03Part02

import utils.getResourceText

fun main(args: Array<String>) {
    val treeTraverse = getResourceText("slopes.txt").split("\n").filter { it.isNotEmpty() }

    val p = arrayOf(
        1 to 1,
        3 to 1,
        5 to 1,
        7 to 1,
        1 to 2
    ).fold(1L) {product, pair -> product * treesEncountered(pair.first, pair.second, treeTraverse)}
    println(p)
}
fun treesEncountered(right: Int, down: Int, slopes: List<String>): Int {
    var atRow = down
    var atCol = right
    val COLS = slopes[0].length

    var treesEncountered = 0;
    while (atRow < slopes.size) {
        if (slopes[atRow][atCol] == '#') {
            treesEncountered++
        }
        atCol = (atCol+right)%COLS
        atRow += down
    }
    return treesEncountered
}
