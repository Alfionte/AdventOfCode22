package day11

import day11.model.Monkey
import day11.model.MonkeyParser.toMonkey
import day11.model.StolenItem
import domain.UseCase
import java.io.File

class MonkeyBusinessUseCase : UseCase {

    private val input = File("src/main/resources/day11/input.txt")

    private fun getMonkeys(): MutableList<Monkey> =
        input
            .readLines()
            .chunked(7)
            .map { monkeyLine ->
                monkeyLine.toMonkey()
            }.sortedBy { it.monkeyNumber }
            .toMutableList()

    private fun MutableList<Monkey>.calculateRounds(rounds: Int): Map<Int, Int> {
        val moneyInspectionsMap = this.associate { it.monkeyNumber to 0 }.toMutableMap()

        repeat(rounds) { currentRound ->
            println("Round: $currentRound")
            forEach { monkey ->
                println("Monkey: $monkey")
                monkey.items.forEach { stolenItem ->
                    moneyInspectionsMap[monkey.monkeyNumber] = moneyInspectionsMap[monkey.monkeyNumber]?.inc() ?: 0.inc()

                    println("Stolen item: $stolenItem")
                    val worryAfterInspection = monkey.operation(stolenItem.worryLevel)
                    println("Stolen item after inspection: $worryAfterInspection")
                    val worryAfterGetBored = worryAfterInspection / 3
                    println("Stolen item after get bored: $worryAfterGetBored")

                    if (monkey.isTestPassed(worryAfterGetBored)) {
                        this[monkey.testPassToMonkeyIndex].items.add(StolenItem(worryAfterGetBored))
                        println("Stolen item test passed, is sent to monkey: ${monkey.testPassToMonkeyIndex}")
                    } else {
                        this[monkey.testFailToMonkeyIndex].items.add(StolenItem(worryAfterGetBored))
                        println("Stolen item test failed, is sent to monkey: ${monkey.testFailToMonkeyIndex}")
                    }
                }
                println()
                monkey.items.clear()
            }
        }
        return moneyInspectionsMap
    }

    override fun run() {
        getMonkeys()
            .calculateRounds(20)
            .toList()
            .sortedByDescending { it.second }
            .take(2)
            .reduce { acc, pair -> acc.first to (acc.second * pair.second) }
            .second
            .also { println("Monkey business : $it") }
    }
}