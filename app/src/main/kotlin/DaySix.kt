package no.designsolutions.advent.app

import no.designsolutions.advent.app.DaySix.transpose
import no.designsolutions.advent.utils.FileReader


fun main() {
    println("Day 5:")

    println(DaySix.matrix.joinToString("\n"))
    val transposed = DaySix.numbers.transpose()
    println(transposed)
    for (i in 0..DaySix.operations.size - 1) {
        val numbers = DaySix.numbers.transpose()
        println(numbers)
    }
}

object DaySix {
    val input = FileReader("day_six_input.txt").text
    val lines = input.lines()
    val matrix = lines.map { it.split(" ").filterNot { it.isBlank() } }
    val operations = matrix.last()
    val numbers = matrix.dropLast(1).map { it.map { numberString -> numberString.toInt() } }.transpose()

    fun operation(numbers: List<Int>, operator: String) {
        when (operator) {
            "+" -> numbers.sum()
            "*" -> numbers.product()
        }
    }

    fun List<Int>.product(): Long {
        var product = 1L
        forEach { product = product.times(it) }
        return product
    }

    fun List<List<Int>>.transpose(): List<List<Int>> {
        val outer = List(first().size) { x ->
            List(size) { y ->
                (this@transpose[y][x])
            }
        }
        return outer
    }
}