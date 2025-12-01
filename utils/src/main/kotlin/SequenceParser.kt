package no.designsolutions.advent.utils

import kotlin.collections.forEachIndexed

class SequenceParser(private val startingPoint: Int, instructions: String) {

    private val instructionList = instructions.trim().split("\n")
    fun executeInstructions(): Int {
        var currentPosition = startingPoint
        var counter = 0
        instructionList.forEachIndexed { index, instruction ->
            val startPosition = currentPosition
            val (direction, steps) = getInstruction(instruction)

            val revolutions = steps.div(100)
            val minor = steps.mod(100)
            val newPosition = (startPosition + minor.times(direction.factor)).mod(100)

            counter += revolutions + (1.takeIf { minor != 0 &&
                (newPosition == 0
                        || startPosition < minor && direction == Direction.Left  && startPosition != 0 
                        || (100 - startPosition) < minor && direction == Direction.Right)

            } ?: 0)
            currentPosition = newPosition
        }
        return counter
    }

    fun executeOneStepAtATime(): Int {
        var currentPosition = startingPoint
        var counter = 0
        instructionList.forEach { instruction ->
            val (direction, steps) = getInstruction(instruction)
            repeat(steps) {
                if (direction == Direction.Right) currentPosition++ else currentPosition--
                currentPosition = currentPosition.mod(100)
                if (currentPosition == 0) counter++
            }
        }
        return counter
    }

    private fun getInstruction(instruction: String): Pair<Direction, Int> {
        val directionChar = instruction.first()
        val direction = Direction.fromChar(directionChar)
        val steps = instruction.drop(1).toInt()
        return direction to steps
    }

    enum class Direction(val factor: Int) {
        Left(-1), Right(1);

        companion object {
            fun fromChar(char: Char): Direction = when (char.uppercase()) {
                "L" -> Left
                "R" -> Right
                else -> error("not a valid direction character")
            }
        }
    }
}