package day5

import day5.model.CrateMovement
import domain.UseCase
import java.io.File


class CraneUseCase : UseCase {

    private val input = File("src/main/resources/day5/input.txt")
    private val schemaIndexes = listOf(1, 5, 9, 13, 17, 21, 25, 29, 33)
    private val regexMoves = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

    private fun getMoves(): List<CrateMovement> {
        val moves = input
            .readLines()
            .drop(10)
            .map {
                val (quantity, from, to) = regexMoves.find(it)!!.destructured
                CrateMovement(quantity.toInt(), from.toInt(), to.toInt())
            }
        return moves
    }

    private fun getCratesSchema(): List<ArrayDeque<Char>> {
        val schema = mutableListOf<ArrayDeque<Char>>().apply { repeat(9) { add(ArrayDeque()) } }
        input
            .readLines()
            .take(8)
            .forEach {
                schemaIndexes.mapIndexed { index: Int, i: Int ->
                    val element = it.getOrNull(i)
                    if (element != null && element != ' ') {
                        schema[index].add(element)
                    }
                }
            }
        return schema
    }

    private fun moveCrates(): String {
        val schema = getCratesSchema()
        val moves = getMoves()

        moves.forEach { movement ->
            repeat(movement.quantity) {
                val crate = schema[movement.from - 1].removeFirst()
                schema[movement.to - 1].addFirst(crate)
            }
        }
        return schema.joinToString(separator = "") { chars: ArrayDeque<Char> -> chars.first().toString() }
    }

    private fun moveCratesImproved(): String {
        val schema = getCratesSchema()
        val moves = getMoves()

        moves.forEach { movement ->
            val crates = mutableListOf<Char>()
            repeat(movement.quantity) {
                crates.add(schema[movement.from - 1].removeFirst())
            }
            schema[movement.to - 1].addAll(0, crates)
        }
        return schema.joinToString(separator = "") { chars: ArrayDeque<Char> -> chars.first().toString() }
    }

    override fun run() {
        moveCrates().also { println("Last crate schema: $it") }
        moveCratesImproved().also { println("Last crate schema improved: $it") }
        println()
    }
}