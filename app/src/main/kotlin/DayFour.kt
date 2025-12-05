package no.designsolutions.advent.app

import no.designsolutions.advent.utils.FileReader
import no.designsolutions.advent.utils.day_four.PaperRollScout

object DayFour {
    val input = FileReader("day_four_input.txt").text
    val scout = PaperRollScout(input)
}