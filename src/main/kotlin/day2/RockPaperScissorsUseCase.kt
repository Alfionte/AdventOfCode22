package day2

import day2.model.MyHand
import day2.model.OpponentHand
import day2.model.RPS
import java.io.File

class RockPaperScissorsUseCase {

    private val input = File("src/main/resources/day2/input.txt")

    fun getPointsForTheGuide(): Int =
        input
            .readLines()
            .sumOf {
                val myHand = MyHand(it[2]).rps
                val opponentHand = OpponentHand(it[0]).rps
                val game = myHand play opponentHand
                game + myHand.value
            }

    private infix fun RPS.play(other: RPS): Int =
        when {
            this == other -> 3
            (this is RPS.Rock && other is RPS.Scissor) || (this is RPS.Scissor && other is RPS.Paper) || (this is RPS.Paper && other is RPS.Rock) -> 6
            else -> 0
        }
}