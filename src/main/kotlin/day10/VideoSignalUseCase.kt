package day10

import day10.model.DrawResult
import day10.model.Instruction
import day10.model.Pixel
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

        forEachIndexed { i, instruction ->
            allCycles.add(i to registry)
            if (signalStrengths.keys.contains(i)) signalStrengths[i] = registry
            if (instruction is Instruction.AddX) registry += instruction.value
        }

        return signalStrengths
            .toList()
            .sumOf { (it.first + 1) * it.second }
    }

    private fun List<List<Instruction>>.drawGrid(onLineDrawn: (List<Pixel>) -> Unit) {
        var registry = 1
        for (i in 0..lastIndex) {
            val drawResult = this[i].drawLine(registry)
            registry = drawResult.registy
            onLineDrawn(drawResult.pixels)
        }
    }

    private fun List<Instruction>.drawLine(registry: Int): DrawResult {
        val drawing = mutableListOf<Pixel>()
        val lineCycle = mutableListOf<Pair<Int, Int>>()
        var internalRegistry = registry

        forEachIndexed { cycle, instruction ->
            lineCycle.add(cycle to registry)
            val pixel = if (cycle.isInSprite(internalRegistry)) Pixel.Lit else Pixel.Dark
            drawing.add(pixel)
            if (instruction is Instruction.AddX) internalRegistry += instruction.value
        }
        return DrawResult(internalRegistry, drawing)
    }

    private infix fun Int.isInSprite(registry: Int) = this == registry || this == registry - 1 || this == registry + 1

    override fun run() {
        getInstructions()
            .toCycles()
            .getSignalStrength(listOf(20, 60, 100, 140, 180, 220))
            .also {
                println("Signal strengths: $it")
                println()
            }

        getInstructions()
            .toCycles()
            .also { println("CRT:") }
            .chunked(40)
            .drawGrid { line ->
                line.forEach { print(it) }
                println()
            }
    }
}