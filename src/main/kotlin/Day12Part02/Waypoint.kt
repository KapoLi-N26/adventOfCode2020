package Day12Part02

import Day12Part02.Direction.*
import kotlin.math.absoluteValue

data class Waypoint(var direction: Direction, var units: Int) {

    fun rotate(degrees: Int, b: Boolean) {
        val moves = (degrees / 90) * if (b) {
            1
        } else {
            -1
        }
        val i = ((DIRECTIONS.indexOf(direction) + moves) + 4) % 4
        direction = DIRECTIONS[i]

        units = direction.multiplier * units.absoluteValue
    }

    fun moveWaypoint(moveWaypoint: Waypoint) {
        if (isPolar(moveWaypoint)) {
            units += moveWaypoint.units
            val sn = units.toBigDecimal().signum()
            if(units != 0 && sn != direction.multiplier.toBigDecimal().signum()) {
                rotate(180, true)
            }
        }
    }

    fun isPolar(input: Waypoint): Boolean {
        return isPolar(input.direction)
    }

    fun isPolar(input: Direction): Boolean {
        if ((direction == NORTH || direction == SOUTH) &&
            (input == NORTH || input == SOUTH)) {
            return true
        } else if ((direction == EAST || direction == WEST) &&
            (input == EAST || input == WEST)) {
            return true
        }
        return false
    }
}
