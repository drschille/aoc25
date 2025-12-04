package no.designsolutions.advent.app

import no.designsolutions.advent.utils.Printer
import no.designsolutions.advent.utils.day_one.SequenceParser
import no.designsolutions.advent.utils.day_one.inputDayOne

object DayOne {

    private val parser = SequenceParser(50, inputDayOne)

    private val testData = """
        L50
        L100
        R99
        R1
        L1
        L99
        L500
        R100
    """.trimIndent()

    fun run() {

        val testAnswer = SequenceParser(50, testData).run {
            executeInstructionsOptimised()
            executeInstructions()
            executeOneStepAtATime()
        }

        Printer("test answer: $testAnswer").printMessage()

        val codeReader = SequenceParser(50, inputDayOne)
        val firstAnswer = codeReader.executeInstructionsOptimised()

        Printer("first try: $firstAnswer").printMessage()
        val finalAnswer = codeReader.executeOneStepAtATime()
        Printer("final answer: $finalAnswer").printMessage()
        parser.executeInstructions()
        parser.executeInstructions()
        parser.executeInstructionsOptimised()
    }
}