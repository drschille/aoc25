package no.designsolutions.advent.utils.day_two

class DayTwoInputReader(input: String) {

    val ranges = input.lines().map {
        it.split("-").let {
            it.first().toLong() to it.last().toLong()
        }
    }

    private fun getListOfNumbers() = ranges.flatMap {
        val (start, finish) = it
        start..finish
    }

    private fun filterNumbersOfEvenLength(): List<Long> = getListOfNumbers().filter { number ->
        number.toString().length.isEven()
    }

    fun filterNumbersThatRepeatTwice(): List<Long> = filterNumbersOfEvenLength().mapNotNull {
        it.takeIf {
            it.toString().hasRepeatingSequence()
        }
    }

    fun filterNumbersThatRepeats(): List<Long> = getListOfNumbers().filter {
        RepeatChecker(it).isRepeating()
    }
}

private fun Int.isEven(): Boolean = this % 2 == 0

private fun String.hasRepeatingSequence(): Boolean {
    val splitPoint = length / 2
    val start = take(splitPoint)
    val end = drop(splitPoint)
    return start == end
}