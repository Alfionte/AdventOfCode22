package day11

import day11.model.Monkey
import day11.model.MonkeyParser.toMonkey
import domain.UseCase
import java.io.File

class MonkeyBusinessUseCase : UseCase {

    private val input = File("src/main/resources/day11/inputTest.txt")

    private fun getMonkeys(): List<Monkey> =
        input
            .readLines()
            .chunked(7)
            .map { monkeyLine ->
                monkeyLine.toMonkey()
            }.sortedBy { it.monkeyNumber }

    override fun run() {
        getMonkeys().onEach { println(it) }
    }
}