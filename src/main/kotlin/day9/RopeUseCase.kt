package day9

import day9.model.MoveDirection
import day9.model.MoveDirectionMapper
import day9.model.RopeNode
import domain.UseCase
import java.awt.Point
import java.io.File

class RopeUseCase : UseCase {

    private val input = File("src/main/resources/day9/input.txt")

    private fun getHeadMoves(): List<MoveDirection> =
        input
            .readLines()
            .map { line -> MoveDirectionMapper.fromLine(line) }


    private fun List<MoveDirection>.moveRope(): Pair<RopeNode.Head, RopeNode.Tail> {
        var head = RopeNode.Head(Point())
        var tail = RopeNode.Tail(Point())

        // For each movement
        forEach { moveDirection ->
            repeat(moveDirection.quantity) {
                head = when (moveDirection) {
                    is MoveDirection.Down -> head.copy(position = Point(head.position.x, head.position.y.dec()))
                    is MoveDirection.Left -> head.copy(position = Point(head.position.x.dec(), head.position.y))
                    is MoveDirection.Right -> head.copy(position = Point(head.position.x.inc(), head.position.y))
                    is MoveDirection.Up -> head.copy(position = Point(head.position.x, head.position.y.inc()))
                }
                println("Head move to: $head")
                println("Tail move to: $tail")
            }
        }
        return head to tail
    }

    override fun run() {
        getHeadMoves()
            .also { println("Direction moves: $it") }
            .moveRope()
            .also { (head, tail) -> println("End") }
    }
}