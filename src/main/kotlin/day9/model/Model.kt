package day9.model

sealed interface MoveDirection {
    data class Up(val quantity: Int) : MoveDirection
    data class Down(val quantity: Int) : MoveDirection
    data class Right(val quantity: Int) : MoveDirection
    data class Left(val quantity: Int) : MoveDirection
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