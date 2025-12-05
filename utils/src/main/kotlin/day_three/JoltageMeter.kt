package no.designsolutions.advent.utils.day_three

class JoltageMeter(input: String, private val batteries: Int = 2) {

    val powerBanks = input.lines()
    val sequences = powerBanks.map { sequence ->
        sequence.map { char -> char.digitToInt() }
    }
    val maxJoltages = sequences.map { getMaxJoltage(it) }

    private fun getMaxJoltage(sequence: List<Int>): Long {
        val sequenceLength = sequence.size
        val map = MutableList(batteries) { 0 }
        sequence.forEachIndexed loop@{ index, number ->
            val batteriesLeft = sequenceLength - index - 1
            map.forEachIndexed{ packIndex, largest ->
                if (largest == 9) return@forEachIndexed
                val slotsLeft = batteries - packIndex - 1
                if (number > largest && batteriesLeft >= slotsLeft) {
                    map[packIndex] = number
                    (1..slotsLeft).forEach {
                        map[packIndex + it] = 0
                    }
                    return@loop
                }
            }
        }

        val totalLargest = map.joinToString("") { it.toString() }.toLong()

        return totalLargest
    }
}