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


    private fun List<MoveDirection>.moveRope(): HashSet<Point> {

        var head = RopeNode.Head(Point())
        var tail = RopeNode.Tail(Point())
        val uniqueTailPoints = HashSet<Point>().apply { add(tail.position) }

        // For each movement
        forEach { moveDirection ->
            repeat(moveDirection.quantity) {
                head = when (moveDirection) {
                    is MoveDirection.Down -> head.copy(position = Point(head.position.x, head.position.y.dec()))
                    is MoveDirection.Left -> head.copy(position = Point(head.position.x.dec(), head.position.y))
                    is MoveDirection.Right -> head.copy(position = Point(head.position.x.inc(), head.position.y))
                    is MoveDirection.Up -> head.copy(position = Point(head.position.x, head.position.y.inc()))
                }
                tail = tail.tailMovement(head)
                uniqueTailPoints.add(tail.position)
            }
        }
        return uniqueTailPoints
    }

    private fun RopeNode.Tail.tailMovement(head: RopeNode.Head): RopeNode.Tail {
        val yDistance = head.position.y - position.y
        val xDistance = head.position.x - position.x

        return when {
            yDistance > 1 -> RopeNode.Tail(Point(head.position.x, position.y.inc()))
            yDistance < -1 -> RopeNode.Tail(Point(head.position.x, position.y.dec()))
            xDistance > 1 -> RopeNode.Tail(Point(position.x.inc(), head.position.y))
            xDistance < -1 -> RopeNode.Tail(Point(position.x.dec(), head.position.y))
            else -> this
        }
    }

    override fun run() {
        getHeadMoves()
            .also { println("Direction moves: $it") }
            .moveRope()
            .count()
            .also { println("Unique tail points: $it") }
    }
}