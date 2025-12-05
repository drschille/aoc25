package no.designsolutions.advent.utils.day_four

class PaperRollScout(private val input: String, private val allowed: Int = 3) {

    val rows = input.lines().size

    val cols = input.lines().first().length

    val matrix: Array<Array<Boolean>> = Array(rows) { row -> Array(cols) { col -> input.lines()[row][col] == '@' } }

    val accessible = matrix.mapIndexed { row, booleans ->
        booleans.mapIndexed { col, isPaperRoll ->
            if (isPaperRoll) {
                checkAdjacent(matrix, row, col, allowed)
            } else false
        }
    }

    val output = input.lines().mapIndexed { row, line ->
        line.mapIndexed { col, char ->
            if (accessible[row][col]) return@mapIndexed 'x' else char
        }.joinToString("")
    }.joinToString("\n")

    private fun checkAdjacent(matrix: Array<Array<Boolean>>, row: Int, col: Int, allowed: Int): Boolean {
        var count = -1 // This is to exclude the paper roll we are checking
        val firstRow = (row - 1).coerceAtLeast(0)
        val lastRow = (row + 1).coerceAtMost(rows - 1)
        val firstCol = (col - 1).coerceAtLeast(0)
        val lastCol = (col + 1).coerceAtMost(cols - 1)
        for (r in firstRow..lastRow) {
            for (c in firstCol..lastCol) {
                if (matrix[r][c]) count++
                if (count > allowed) return false
            }
        }
        return true
    }

    fun printInput() {
        println(input)
    }

    fun printOutput() {
        println(output)
    }
}