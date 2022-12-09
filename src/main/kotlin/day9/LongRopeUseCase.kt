package day9

import day9.model.MoveDirection
import day9.model.MoveDirectionMapper
import day9.model.RopeNode
import domain.UseCase
import java.awt.Point
import java.io.File

class LongRopeUseCase : UseCase {

    @Suppress("unused")
    private val inputTest = File("src/main/resources/day9/inputTest.txt")
    private val input = File("src/main/resources/day9/input.txt")

    private fun getHeadMoves(): List<MoveDirection> =
        input
            .readLines()
            .map { line -> MoveDirectionMapper.fromLine(line) }


    private fun List<MoveDirection>.moveRope(
        knotsSize: Int,
        onMoveDirection: (RopeNode.Head, List<RopeNode.Knot>) -> Unit
    ): HashSet<Point> {

        var head = RopeNode.Head(Point())
        val knots = MutableList(knotsSize) { RopeNode.Knot(Point()) }
        val uniqueTailPoints = HashSet<Point>().apply { add(knots.last().position) }

        // For each movement
        forEach { moveDirection ->
            repeat(moveDirection.quantity) {
                head = when (moveDirection) {
                    is MoveDirection.Down -> head.copy(position = Point(head.position.x, head.position.y.dec()))
                    is MoveDirection.Left -> head.copy(position = Point(head.position.x.dec(), head.position.y))
                    is MoveDirection.Right -> head.copy(position = Point(head.position.x.inc(), head.position.y))
                    is MoveDirection.Up -> head.copy(position = Point(head.position.x, head.position.y.inc()))
                }

                knots[0] = knots[0].tailMovement(head)
                for (i in 1..knots.lastIndex) {
                    knots[i] = knots[i].tailMovement(knots[i - 1])
                }
                uniqueTailPoints.add(knots.last().position)
            }
            onMoveDirection(head, knots)
        }
        return uniqueTailPoints
    }

    private fun RopeNode.Knot.tailMovement(head: RopeNode): RopeNode.Knot {
        val yDistance = head.position.y - position.y
        val xDistance = head.position.x - position.x

        return when {
            yDistance > 1 && xDistance > 1 -> RopeNode.Knot(Point(position.x.inc(), position.y.inc()))
            yDistance < -1 && xDistance < -1 -> RopeNode.Knot(Point(position.x.dec(), position.y.dec()))
            yDistance < -1 && xDistance > 1 -> RopeNode.Knot(Point(position.x.inc(), position.y.dec()))
            yDistance > 1 && xDistance < -1 -> RopeNode.Knot(Point(position.x.dec(), position.y.inc()))
            yDistance > 1 -> RopeNode.Knot(Point(head.position.x, position.y.inc()))
            yDistance < -1 -> RopeNode.Knot(Point(head.position.x, position.y.dec()))
            xDistance > 1 -> RopeNode.Knot(Point(position.x.inc(), head.position.y))
            xDistance < -1 -> RopeNode.Knot(Point(position.x.dec(), head.position.y))
            else -> this
        }
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    override fun run() {
        @Suppress("ControlFlowWithEmptyBody")
        getHeadMoves()
            .also { println("Direction moves: $it") }
            .moveRope(9) { head, knots ->
                // printIntermediate(15, head, knots)
            }
            .also {
                // printResult(15, it)
            }
            .count()
            .also { println("Unique tail points: $it") }
    }
}