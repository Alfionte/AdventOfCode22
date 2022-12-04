package day4

import java.io.File

class CleaningAreasUseCase {

    private val input = File("src/main/resources/day4/input.txt")
    private val regex = "(\\d+)-(\\d+),(\\d+)-(\\d+)".toRegex()

    fun getOverlapAssignment(): Int =
        input
            .readLines()
            .mapNotNull {
                val (min1, max1, min2, max2) = getRanges(it)
                val range1 = (min1.toInt()..max1.toInt())
                val range2 = (min2.toInt()..max2.toInt())
                if (range1 isIncludedIn range2 || range2 isIncludedIn range1) Unit else null
            }
            .count()

    private fun getRanges(line: String) = regex.find(line)!!.destructured

    private infix fun IntRange.isIncludedIn(range: IntRange) = this.first >= range.first && this.last <= range.last

}