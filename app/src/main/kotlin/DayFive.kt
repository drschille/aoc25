package no.designsolutions.advent.app

import no.designsolutions.advent.utils.FileReader

object DayFive {
    val input = FileReader("day_five_input.txt").text
    val ranges = input.substringBefore("\n\n")
    val ingredients = input.substringAfter("\n\n")

    val rangesExtremes = ranges.lines().map { it.split("-").run {
        first().toLong() to last().toLong()
    } }

    val ingredientsLong = ingredients.lines().map { it.toLong() }

    val freshIngredients = ingredientsLong.map { ingredient -> rangesExtremes.any { range -> ingredient in range.first..range.second } }

    fun startup() {
        println(ranges)
        println("ingredients:")
        println(ingredients)
    }

    fun countFresh() = freshIngredients.count { it }
}