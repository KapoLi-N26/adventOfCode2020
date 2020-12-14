package Day12Part02

import Day12Part02.Direction.EAST
import Day12Part02.Direction.NORTH
import Day12Part02.Direction.SOUTH
import Day12Part02.Direction.WEST
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

    var waypoints = arrayListOf(Waypoint(EAST, 10),Waypoint(NORTH, 1))
    var shipEastWest = 0
    var shipNorthSouth = 0

    for(i in naviationInstructions.indices) {
        val currentInstruction = naviationInstructions[i].first
        val currentM = naviationInstructions[i].second
        if (currentInstruction == 'F') {
            shipEastWest += currentM * waypoints.filter { it.direction == EAST || it.direction == WEST }.first().units
            shipNorthSouth += currentM * waypoints.filter { it.direction == NORTH || it.direction == SOUTH }.first().units
        } else if(currentInstruction == 'R' || currentInstruction == 'L') {
            waypoints.forEach {
                it.rotate(currentM, currentInstruction == 'R')
            }
        } else {
            waypoints.forEach {
                val d = getDirection(currentInstruction)
                it.moveWaypoint(Waypoint(d, d.multiplier * currentM))
            }
        }
    }
    println(shipEastWest.absoluteValue + shipNorthSouth.absoluteValue)
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
