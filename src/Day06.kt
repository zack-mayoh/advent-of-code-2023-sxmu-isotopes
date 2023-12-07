import kotlin.math.*

fun main() {

    fun countWinningRaces(time: Long, record: Long): Long {
//        x^2 - time x  + record = 0
//        x = ( time +/- sqrt(time^2 - 4*record))/ 2
        val discriminant = time.toDouble().pow(2.0) - (4 * record)
        if (discriminant < 0) return 0

        val root1 = (time - sqrt(discriminant)) / 2
        val root2 = (time + sqrt(discriminant)) / 2

        val lowerBound = if (ceil(root1) == root1) root1 + 1 else ceil(root1)
        val upperBound = if (floor(root2) == root2) root2 - 1 else floor(root2)

        val diff = upperBound - lowerBound
        if (diff < 0) return 0
        return diff.toLong() + 1
    }

    fun part1(input: List<String>): Long {
        fun parseLine(line: String) =
            line.split(":")[1]
                .trim()
                .split("\\s+".toRegex())
                .map { it.toLong() }

        fun parseRaces(input: List<String>): Map<Long,Long> {
            return parseLine(input[0])
                .zip(parseLine(input[1]))
                .toMap()
        }

        val timeToRecord = parseRaces(input)
        return timeToRecord
            .map { (time,record) -> countWinningRaces(time, record) }
            .fold(1) { total, next -> total * next }
    }

    fun part2(input: List<String>): Long {
        fun parseLine(line: String) =
            line.split(":")[1]
                .trim()
                .split("\\s+".toRegex())
                .joinToString("")
                .toLong()

        fun parseRace(input: List<String>): Pair<Long,Long> {
            return Pair(parseLine(input[0]), parseLine(input[1]))
        }

        val (time, record) = parseRace(input)
        return countWinningRaces(time, record)
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day06_test")
    check(part1(testInput1) == 288.toLong())
    val testInput2 = readInput("Day06_test")
    check(part2(testInput2) == 71503.toLong())

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
