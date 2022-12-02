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

data class MatchStrategy(private val value: Char, private val opponentHand: OpponentHand) {
    val myHand: RPS
        get() = when (value) {
            'X' -> opponentHand.rps.lose()
            'Y' -> opponentHand.rps
            else -> opponentHand.rps.win()
        }
}

private fun RPS.lose(): RPS =
    when (this) {
        is RPS.Rock -> RPS.Scissor
        is RPS.Scissor -> RPS.Paper
        is RPS.Paper -> RPS.Rock
    }

private fun RPS.win(): RPS =
    when (this) {
        is RPS.Scissor -> RPS.Rock
        is RPS.Paper -> RPS.Scissor
        is RPS.Rock -> RPS.Paper
    }