package no.designsolutions.advent.app

import no.designsolutions.advent.utils.Printer

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

    Printer("Day 3:").printMessage()
    DayThree.testOne()
    DayThree.partOne()
    DayThree.testTwo()
    DayThree.partTwo()

}
