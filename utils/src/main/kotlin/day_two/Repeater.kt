package no.designsolutions.advent.utils.day_two

data class RepeatChecker(
    val number: Long,
) {
    private val length: Int = number.toString().length
    fun isRepeating(): Boolean {
        return (1..length.div(2)).any { digits ->
            val n = number.toString()
            if (length % digits != 0) return@any false
            val repeater = n.take(digits)
            val chunked = n.chunked(digits)
            chunked.all { it == repeater }
        }
    }
}
