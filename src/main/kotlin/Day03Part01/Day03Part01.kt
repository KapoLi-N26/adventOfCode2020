package Day03Part01

import utils.getResourceText

fun main(args: Array<String>) {
    val treeTraverse = getResourceText("slopes.txt").split("\n").filter { it.isNotEmpty() }
    var atRow = 1
    var atCol = 3
    val COLS = treeTraverse[0].length

    var treesEncountered = 0;
    while (atRow < treeTraverse.size) {
        if (treeTraverse[atRow][atCol] == '#') {
            treesEncountered++
        }
        atCol = (atCol+3)%COLS
        atRow++
    }
    print(treesEncountered)
}
