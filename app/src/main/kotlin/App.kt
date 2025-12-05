package no.designsolutions.advent.app

import no.designsolutions.advent.utils.Printer
import kotlin.system.measureTimeMillis

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val introduction = "Welcome to Advent of Code Solutions!"
    Printer(introduction).printMessage()

//    Printer("The first task is to open the safe with the code").printMessage()
//
//    DayOne.run()
//
//    Printer("Day 2:").printMessage()
//
//    println("Remainder of 7:1 = ${7 % 1}")

//    DayTwo.testOne()
//    DayTwo.partOne()
//    DayTwo.testTwo()
//    DayTwo.partTwo()

//    Printer("Day 3:").printMessage()
//    DayThree.testOne()
//    measureTimeMillis {
//        DayThree.partOne()
//    }.print("Part 1")
//    DayThree.testTwo()
//    measureTimeMillis {
//        DayThree.partTwo()
//    }.print("Part 2")
//
//    Printer("Day 4:").printMessage()
//    println("Input Matrix:")
//    DayFour.scout.printInput()
//    println("\nOutput Matrix:")
//    DayFour.scout.printOutput()
//
//    println("\nNumber of accessible paper rolls: ${DayFour.scout.accessible.flatMap { it.toList() }.count { it }}")
//    val rollsAtStart = DayFour.scout.matrix.sumOf { it.count { it } }
//    val rollsAfterFinish = DayFour.scout.removeRolls().sumOf { it.count {it} }
//    val totalRemoved = rollsAtStart - rollsAfterFinish
//    println("Total number of rolls removed: $totalRemoved")
    Printer("Day 5:").printMessage()

    DayFive.startup()
    println("Number of fresh ingredients: ${DayFive.countFresh()}")
}

fun Long.print(label: String = "") {
    println("$label took $this milliseconds to run")
}