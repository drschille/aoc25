package no.designsolutions.advent.utils.day_one

class SequenceParser(private val startingPoint: Int, instructions: String) {

    private val instructionList = instructions.trim().split("\n")

    fun executeInstructionsOptimised(): Int {
        var pos = startingPoint
        var count = 0

        for (instruction in instructionList) {
            val dir = if (instruction[0] == 'R' || instruction[0] == 'r') 1 else -1
            val steps = instruction.substring(1).toInt()

            val revs = steps / 100
            val minor = steps % 100

            val start = pos

            // Check if minor part crosses zero
            val crossesZero = when (dir) {
                Direction.Right.factor -> start > 0 && start + minor >= 100
                Direction.Left.factor  -> start > 0 && minor >= start
                else -> error("can't happen")
            }

            count += revs + if (minor != 0 && crossesZero) 1 else 0

            // Fast modulo step (avoid mod())
            val raw = start + dir * minor
            pos = when {
                raw >= 100 -> raw - 100
                raw < 0    -> raw + 100
                else       -> raw
            }
        }

        return count
    }



    fun executeInstructions(): Int {
        var currentPosition = startingPoint
        var counter = 0
        instructionList.forEachIndexed { index, instruction ->
            val startPosition = currentPosition
            val (direction, steps) = getInstruction(instruction)

            val revolutions = steps.div(100)
            val minor = steps % 100
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