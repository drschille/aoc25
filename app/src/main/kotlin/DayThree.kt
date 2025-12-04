package no.designsolutions.advent.app

import no.designsolutions.advent.utils.day_three.JoltageMeter
import no.designsolutions.advent.utils.day_three.dayThreeInput

object DayThree {
    private val testData = """
        987654321111111
        811111111111119
        234234234234278
        818181911112111
    """.trimIndent()

    fun testOne() {
        val testMeter = JoltageMeter(testData)
        println("Test: Max joltages: ${testMeter.maxJoltages}")
        println("Test: Sum of joltages: ${testMeter.maxJoltages.sum()}")
    }

    fun partOne() {
        val joltageMeter = JoltageMeter(dayThreeInput)
        println("Max joltages: ${joltageMeter.maxJoltages}")
        println("Sum of joltages: ${joltageMeter.maxJoltages.sum()}")
    }

    fun testTwo() {
        val testMeter = JoltageMeter(testData, 12)
        println("Test with 12 batteries: Max joltages: ${testMeter.maxJoltages}")
        println("Test with 12 batteries: Sum of joltages: ${testMeter.maxJoltages.sum()}")
    }

    fun partTwo() {
        val joltageMeter = JoltageMeter(dayThreeInput, 12)
        println("Max joltages with 12 batteries: ${joltageMeter.maxJoltages}")
        println("Sum of joltages with 12 batteries: ${joltageMeter.maxJoltages.sum()}")
    }

}