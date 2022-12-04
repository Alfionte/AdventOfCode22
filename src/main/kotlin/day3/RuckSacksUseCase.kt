package day3

import domain.UseCase
import java.io.File

class RuckSacksUseCase : UseCase{

    private val input = File("src/main/resources/day3/input.txt")

    override fun run() {
        getRuckSackPriorities().also { println("RuckSack priorities sum: $it") }
        getBadgePriorities().also { println("Badge priorities sum: $it") }
        println()
    }

    fun getRuckSackPriorities(): Int =
        input
            .readLines()
            .sumOf { getDuplicate(it).getPriority() }

    private fun getDuplicate(string: String): Char {
        val (first, second) = string.chunked(string.length / 2)
        val firstSet = first.toSet()
        return second.find { char -> firstSet.contains(char) }!!
    }

    fun getBadgePriorities() =
        input
            .readLines()
            .chunked(3)
            .sumOf {
                val (first, second, third) = it
                val firstSet = first.toSet()
                val secondSet = second.toSet()
                val thirdSet = third.toSet()
                val map = mutableMapOf<Char, Int>()
                firstSet.forEach { char -> map[char] = map.getOrDefault(char, 0).inc() }
                secondSet.forEach { char -> map[char] = map.getOrDefault(char, 0).inc() }
                thirdSet.forEach { char -> map[char] = map.getOrDefault(char, 0).inc() }

                map.toList().find { element -> element.second == 3 }!!.first.getPriority()
            }


    private fun Char.getPriority(): Int = if (isLowerCase()) code - 96 else code - 38

}