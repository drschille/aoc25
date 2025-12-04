package no.designsolutions.advent.app

import no.designsolutions.advent.utils.day_two.DayTwoInputReader
import no.designsolutions.advent.utils.day_two.inputDayTwo

object DayTwo {

    private val testData =
        """
            11-22
            95-115
            998-1012
            1188511880-1188511890
            222220-222224
            1698522-1698528
            446443-446449
            38593856-38593862
            565653-565659
            824824821-824824827
            2121212118-2121212124
        """.trimIndent()

    fun testOne() {
        val testRunner = DayTwoInputReader(testData)
        val invalidIds = testRunner.filterNumbersThatRepeatTwice()
        val testInvalid = invalidIds.joinToString(", ")
        println("Test invalid ids are: $testInvalid")
    }

    fun partOne() {
        val runner = DayTwoInputReader(inputDayTwo)
        val invalidIds = runner.filterNumbersThatRepeatTwice()
        val output = invalidIds.joinToString(", ")
        println("Invalid ids are: $output")
        println("The sum of the invalid ids is: ${invalidIds.sum()}")
    }

    fun testTwo() {
        val testRunner = DayTwoInputReader(testData)
        val invalidIds = testRunner.filterNumbersThatRepeats()
        val output = invalidIds.joinToString(", ")
        println("Test: Invalid ids are: $output")
        println("Test: The sum of the invalid ids is: ${invalidIds.sum()}")
    }

    fun partTwo() {
        val runner = DayTwoInputReader(inputDayTwo)
        val invalidIds = runner.filterNumbersThatRepeats()
        val output = invalidIds.joinToString(", ")
        println("Invalid ids are: $output")
        println("The sum of the invalid ids is: ${invalidIds.sum()}")
    }
}