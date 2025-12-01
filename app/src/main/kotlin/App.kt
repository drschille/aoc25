package no.designsolutions.advent.app

import no.designsolutions.advent.utils.Printer
import no.designsolutions.advent.utils.SequenceParser

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val introduction = "Welcome to Advent of Code Solutions!"
    Printer(introduction).printMessage()

    Printer("The first task is to open the safe with the code").printMessage()

    val testData = """
        L50
        L100
        R99
        R1
        L1
        L99
        L500
        R100
    """.trimIndent()

    val testAnswer = SequenceParser(50, testData).executeInstructions()
    Printer("test answer: $testAnswer").printMessage()

    val codeReader = SequenceParser(50, inputDayOne)
    val firstAnswer = codeReader.executeInstructions()

    Printer("first try: $firstAnswer").printMessage()
    val finalAnswer = codeReader.executeOneStepAtATime()
    Printer("final answer: $finalAnswer").printMessage()
}
