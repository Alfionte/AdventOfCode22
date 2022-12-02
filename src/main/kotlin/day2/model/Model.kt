package day2.model

sealed class RPS(val value: Int) {
    object Rock : RPS(1)
    object Paper : RPS(2)
    object Scissor : RPS(3)

}

data class OpponentHand(private val value: Char) {
    val rps: RPS
        get() = when (value) {
            'A' -> RPS.Rock
            'B' -> RPS.Paper
            else -> RPS.Scissor
        }
}

data class MyHand(private val value: Char) {
    val rps: RPS
        get() = when (value) {
            'X' -> RPS.Rock
            'Y' -> RPS.Paper
            else -> RPS.Scissor
        }
}