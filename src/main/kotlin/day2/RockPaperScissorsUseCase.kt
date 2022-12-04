package day2

import day2.model.MatchStrategy
import day2.model.MyHand
import day2.model.OpponentHand
import day2.model.RPS
import domain.UseCase
import java.io.File

class RockPaperScissorsUseCase : UseCase {

    private val input = File("src/main/resources/day2/input.txt")

    override fun run() {
        getPointsForTheGuide().also { println("Points from following the guide: $it") }
        getPointsForTheGuideV2().also { println("Points from following the guide V2: $it") }
        println()
    }

    private fun getPointsForTheGuide(): Int =
        input
            .readLines()
            .sumOf {
                val myHand = MyHand(it[2]).rps
                val opponentHand = OpponentHand(it[0]).rps
                val game = myHand play opponentHand
                game + myHand.value
            }

    private fun getPointsForTheGuideV2(): Int =
        input
            .readLines()
            .sumOf {
                val opponentHand = OpponentHand(it[0])
                val myHand = MatchStrategy(it[2], opponentHand).myHand
                val game = myHand play opponentHand.rps
                game + myHand.value
            }

    private infix fun RPS.play(other: RPS): Int =
        when {
            this == other -> 3
            (this is RPS.Rock && other is RPS.Scissor) || (this is RPS.Scissor && other is RPS.Paper) || (this is RPS.Paper && other is RPS.Rock) -> 6
            else -> 0
        }


}