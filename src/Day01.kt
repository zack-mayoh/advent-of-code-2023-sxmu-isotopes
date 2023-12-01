fun main() {

    val numberMap = mapOf(
        "zero" to 0,
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    fun part1(input: List<String>): Int {
        fun getFirstDigit(line: String) = line.first(Char::isDigit).digitToInt()

        fun getCalibrationValue(line: String): Int {
            return getFirstDigit(line.reversed()) + getFirstDigit(line) * 10
        }

        return input.sumOf(::getCalibrationValue)
    }

    fun part2(input: List<String>): Int {
        fun getFirstDigit(line: String, isReversed: Boolean): Int {
            line.indices.forEach { i ->
                when {
                    line[i].isDigit() -> return line[i].digitToInt()
                }
                (3 until 6).forEach { j ->
                    when {
                        i + j <= line.length -> {
                            var key = (line.subSequence(i, i + j))
                            key = if (isReversed) key.reversed().toString() else key
                            when {
                                numberMap.containsKey(key) -> return numberMap[key]!!
                            }
                        }
                    }
                }
            }
            return -1
        }

        fun getCalibrationValue(line: String): Int {
            return getFirstDigit(line.reversed(), true) + getFirstDigit(line, false) * 10
        }

        return input.sumOf(::getCalibrationValue)
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day01_test1")
    check(part1(testInput1) == 142)
    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
