package day9

import day9.model.MoveDirection
import day9.model.MoveDirectionMapper
import domain.UseCase
import java.io.File

class RopeUseCase : UseCase {

    private val input = File("src/main/resources/day9/input.txt")

    private fun getHeadMoves(): List<MoveDirection> =
        input
            .readLines()
            .map { line -> MoveDirectionMapper.fromLine(line) }


    override fun run() {
        getHeadMoves().also { println("Direction moves: $it") }
    }
}