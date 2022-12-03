package day3

import java.io.File

class RuckSacksUseCase {

    private val input = File("src/main/resources/day3/input.txt")

    fun getRuckSackPriorities(): Int =
        input
            .readLines()
            .sumOf { getDuplicate(it).getPriority() }

    private fun getDuplicate(string: String): Char {
        val (first, second) = string.chunked(string.length / 2)
        val firstSet = first.toSet()
        return second.find { char -> firstSet.contains(char) }!!
    }

    private fun Char.getPriority(): Int = if (isLowerCase()) code - 96 else code - 38

}