package day1

import domain.UseCase
import java.io.File
import java.lang.Integer.max

class GetMaxCaloriesUseCase : UseCase{

    private val input = File("src/main/resources/day1/input.txt")

    override fun run() {
        getMaxCalories().also { println("Get max calories: $it") }
        getTop3Count().also { println("Top 3 calories count: $it") }
        println()
    }

    private fun getCaloriesPerElf(): List<Int> {
        val list = mutableListOf<Int>()
        var currentCount = 0

        input.forEachLine { line ->
            if (line.isNotEmpty()) {
                currentCount += line.toInt()
            } else {
                list.add(currentCount)
                currentCount = 0
            }
        }
        return list
    }

    private fun getMaxCalories(): Int {
        var max = 0
        var currentCount = 0

        input.forEachLine { line ->
            if (line.isNotEmpty()) {
                currentCount += line.toInt()
            } else {
                max = max(currentCount, max)
                currentCount = 0
            }
        }
        return max
    }

    fun getTop3Count(): Int = getCaloriesPerElf().sortedDescending().take(3).sum()

}