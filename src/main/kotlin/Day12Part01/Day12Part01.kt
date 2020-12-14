package Day12Part01

import Day12Part01.Direction.EAST
import Day12Part01.Direction.NORTH
import Day12Part01.Direction.SOUTH
import Day12Part01.Direction.WEST
import utils.getResourceText
import java.lang.Exception
import kotlin.math.absoluteValue
import kotlin.streams.toList

enum class Direction(val multiplier: Int) {
    NORTH(1),
    EAST(1),
    SOUTH(-1),
    WEST(-1)
}

val DIRECTIONS = arrayListOf<Direction>(NORTH, EAST, SOUTH, WEST)

fun main(args: Array<String>) {
    var naviationInstructions = getResourceText("navigation.txt").trim()
        .split("\n")
        .stream()
        .filter{it.isNotEmpty()}
        .map { it[0] to it.substring(1).toInt() }
        .toList()

    var facing = EAST
    var eastwest = 0
    var northsouth = 0

    for(i in naviationInstructions.indices) {
        val currentInstruction = naviationInstructions[i].first
        if (currentInstruction == 'F') {
            val add = facing.multiplier * naviationInstructions[i].second
            when(facing) {
                EAST, WEST -> eastwest+=add
                else -> northsouth+=add
            }
        } else if(currentInstruction == 'R' || currentInstruction == 'L') {
            facing = getNewFacingDirection(facing, naviationInstructions[i].second, currentInstruction == 'R')
        } else {
            val direction = getDirection(currentInstruction)
            if (direction == NORTH || direction == SOUTH) {
                northsouth += (direction.multiplier * naviationInstructions[i].second)
            } else {
                eastwest += (direction.multiplier * naviationInstructions[i].second)
            }
        }
    }
    println(eastwest.absoluteValue + northsouth.absoluteValue)
}

fun getNewFacingDirection(currFacing: Direction, degrees: Int, b: Boolean): Direction {
    val moves = (degrees / 90) * if (b) {
        1
    } else {
        -1
    }
    val i = ((DIRECTIONS.indexOf(currFacing) + moves) + 4) % 4
    return DIRECTIONS[i]
}

fun getDirection(char: Char): Direction {
    return when(char) {
        'N' -> NORTH
        'E' -> EAST
        'S' -> SOUTH
        'W' -> WEST
        else -> throw Exception("unrecognized $char")
    }
}
