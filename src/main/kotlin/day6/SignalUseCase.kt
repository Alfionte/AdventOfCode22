package day6

import domain.UseCase
import java.io.File

class SignalUseCase : UseCase {

    private val input = File("src/main/resources/day6/input.txt")


    private fun getFirstCharAfterStart(): Int {
        val stream = input.readText()
        for (i in 0..stream.length - 3) {
            val set = mutableSetOf<Char>().apply {
                add(stream[i])
                add(stream[i + 1])
                add(stream[i + 2])
                add(stream[i + 3])
            }
            if (set.size == 4) return i + 4
        }
        return -1
    }

    override fun run() {
        getFirstCharAfterStart().also { println("Get first char after start sequence: index: $it") }
        println()
    }
}