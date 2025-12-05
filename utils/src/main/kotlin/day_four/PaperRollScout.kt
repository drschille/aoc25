package no.designsolutions.advent.utils.day_four

class PaperRollScout(private val input: String, private val allowed: Int = 3) {

    val rows = input.lines().size

    val cols = input.lines().first().length

    val matrix: Array<Array<Boolean>> = Array(rows) { row -> Array(cols) { col -> input.lines()[row][col] == '@' } }

    val accessible = getAccessible(matrix)

    val output = updateMap(input, accessible)

    private fun getAccessible(matrix: Array<Array<Boolean>>): Array<Array<Boolean>> =
        matrix.mapIndexed { row, booleans ->
            booleans.mapIndexed { col, isPaperRoll ->
                if (isPaperRoll) {
                    checkAdjacent(matrix, row, col, allowed)
                } else false
            }.toTypedArray()
        }.toTypedArray()

    private fun updateMap(input: String, accessible: Array<Array<Boolean>>, replacementChar: Char = 'x'): String =
        input.lines().mapIndexed { row, line ->
            line.mapIndexed { col, char ->
                if (accessible[row][col]) return@mapIndexed replacementChar else char
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

    fun removeRolls(
        map: String = this.input,
        matrix: Array<Array<Boolean>> = this.matrix,
        accessible: Array<Array<Boolean>> = this.accessible,
        level: Int = 0
    ): Array<Array<Boolean>> {
        val rollsAfterRemoved = removeAccessibleOnes(matrix, accessible)
        val accessibleAfterRemoved = getAccessible(rollsAfterRemoved)
        updateMap(map, accessible).let {
            print(it, level)
        }
        return if (accessibleAfterRemoved.sumOf { booleans -> booleans.count { it } } == 0) {
            println("finished on round $level")
            rollsAfterRemoved
        } else {
            println("continue to round ${level + 1}")
            removeRolls(
                map = updateMap(map,accessible, '.'),
                matrix = rollsAfterRemoved,
                accessible = accessibleAfterRemoved,
                level = level + 1
            )
        }
    }

    fun print(map: String, level: Int) {
        println("")
        println("Map level $level:\n$map")
    }

    fun printInput() {
        println(input)
    }

    fun printOutput() {
        println(output)
    }

    companion object {
        fun removeAccessibleOnes(
            matrix: Array<Array<Boolean>>,
            accessible: Array<Array<Boolean>>
        ): Array<Array<Boolean>> {
            return matrix.mapIndexed { row, booleans ->
                booleans.mapIndexed { col, paperRoll ->
                    if (accessible[row][col]) false else paperRoll
                }.toTypedArray()
            }.toTypedArray()
        }
    }
}