package day6

import domain.UseCase
import java.io.File

class SignalUseCase : UseCase {

    private val input = File("src/main/resources/day6/input.txt")

    private fun getMessage(startSequence: Int): Int {
        val stream = input.readText()
        for (i in 0 until stream.length - startSequence) {
            val subSequence = stream.subSequence(i, i + startSequence)
            val set = subSequence.toSet()
            if (set.size == startSequence) return i + startSequence
        }
        return -1
    }

    override fun run() {
        getMessage(4).also { println("Get message after start sequence (4): index: $it") }
        getMessage(14).also { println("Get message after start sequence (14): index: $it") }
        println()
    }
}