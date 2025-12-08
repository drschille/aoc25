package no.designsolutions.advent.utils.day_eight

import kotlin.math.pow
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int, val z: Int) {
    infix fun distanceToPoint(other: Point) : Float {
        val xSquared = (other.x - this.x).toFloat().pow(2)
        val ySquared = (other.y - this.y).toFloat().pow(2)
        val zSquared = (other.z - this.z).toFloat().pow(2)
        return sqrt(xSquared + ySquared + zSquared)
    }
}
