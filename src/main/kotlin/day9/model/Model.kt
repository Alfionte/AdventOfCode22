package day9.model

import java.awt.Point

sealed interface RopeNode {
    data class Head(val position: Point)
    data class Tail(val position: Point)
}

sealed class MoveDirection(open val quantity: Int) {
    data class Up(override val quantity: Int) : MoveDirection(quantity)
    data class Down(override val quantity: Int) : MoveDirection(quantity)
    data class Right(override val quantity: Int) : MoveDirection(quantity)
    data class Left(override val quantity: Int) : MoveDirection(quantity)
}

object MoveDirectionMapper {
    private const val moveDirectionRegex = "(\\S) (\\d+)"
    fun fromLine(line: String): MoveDirection =
        moveDirectionRegex
            .toRegex()
            .find(line)
            ?.destructured
            ?.let { (directionString, quantity) ->
                when (directionString) {
                    "U" -> MoveDirection.Up(quantity.toInt())
                    "D" -> MoveDirection.Down(quantity.toInt())
                    "L" -> MoveDirection.Left(quantity.toInt())
                    "R" -> MoveDirection.Right(quantity.toInt())
                    else -> throw IllegalStateException("")
                }
            }!!
}