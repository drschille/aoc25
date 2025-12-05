package no.designsolutions.advent.app

import no.designsolutions.advent.utils.FileReader
import java.lang.Long.max
import kotlin.math.min

object DayFive {
    val input = FileReader("day_five_input.txt").text
    val ranges = input.substringBefore("\n\n")
    val ingredients = input.substringAfter("\n\n")

    val rangesExtremes = ranges.lines().map {
        it.split("-").run {
            first().toLong() to last().toLong()
        }
    }

    val rangesSorted = rangesExtremes.sortedBy { it.first }

    val ingredientsLong = ingredients.lines().map { it.toLong() }

    val freshIngredients =
        ingredientsLong.map { ingredient -> rangesExtremes.any { range -> ingredient in range.first..range.second } }

    fun startup() {
        println(ranges)
        println("ingredients:")
        println(ingredients)
    }

    fun countFresh() = freshIngredients.count { it }

    private fun rangeOverlap(rangeOne: Pair<Long, Long>, rangeTwo: Pair<Long, Long>): Boolean =
        !(rangeOne.max() < rangeTwo.min() || rangeOne.min() > rangeTwo.max())

    private fun merge(pair: Pair<Pair<Long, Long>, Pair<Long, Long>>) = with(pair) {
        if (rangeOverlap(first, second)) {
            listOf(min(first.first, second.first) to max(first.second, second.second))
        } else {
            listOf(first, second)
        }
    }

    fun merger(): List<Pair<Long, Long>> {
        var buckets = listOf(rangesSorted.first())
        for (i in 1..rangesSorted.size - 1) {
            val merged = merge(buckets.last() to rangesSorted[i])
            buckets = buckets.dropLast(1) + merged
        }
//        println(buckets)
        return buckets.toList()
    }
}

fun Pair<Long, Long>.max(): Long = max(first, second)
fun Pair<Long, Long>.min(): Long = min(first, second)
