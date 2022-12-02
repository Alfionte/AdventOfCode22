package day1

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import utils.ReadUtils
import java.io.File
import java.lang.Integer.max

class GetMaxCaloriesStreamUseCase {

    private val input = File("src/main/resources/day1/input.txt")
    private val readUtils = ReadUtils(input)

    suspend fun getMaxCalories(): Int =
        readUtils
            .getBlocksStream()
            .map { it.maxOf { string -> string.toInt() } }
            .toList()
            .max()
}