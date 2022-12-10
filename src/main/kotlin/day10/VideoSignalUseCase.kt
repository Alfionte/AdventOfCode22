package day10

import day10.model.Instruction
import day10.model.toInstruction
import domain.UseCase
import java.io.File

class VideoSignalUseCase : UseCase {

    private val input = File("src/main/resources/day10/input.txt")

    private fun getInstructions(): List<Instruction> =
        input
            .readLines()
            .map { line -> line.toInstruction() }


    private fun List<Instruction>.toCycles(): MutableList<Instruction> {
        val cycles = mutableListOf<Instruction>()
        this.map {
            if (it is Instruction.AddX) {
                cycles.add(Instruction.Noop)
                cycles.add(it)
            } else {
                cycles.add(Instruction.Noop)
            }
        }
        return cycles
    }

    private fun List<Instruction>.getSignalStrength(cycleChecks: List<Int>): Int {
        val allCycles = mutableListOf<Pair<Int, Int>>()

        val signalStrengths = cycleChecks.associate { (it - 1) to 0 }.toMutableMap()
        var registry = 1

        toCycles().forEachIndexed { i, instruction ->
            allCycles.add(i to registry)
            if (signalStrengths.keys.contains(i)) signalStrengths[i] = registry
            if (instruction is Instruction.AddX) registry += instruction.value
        }

        return signalStrengths
            .toList()
            .sumOf { (it.first + 1) * it.second }
    }

    override fun run() {
        getInstructions()
            .getSignalStrength(listOf(20, 60, 100, 140, 180, 220))
            .also {
                println("Signal strengths: $it")
                println()
            }
    }
}