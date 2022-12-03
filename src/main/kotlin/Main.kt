import day1.GetMaxCaloriesUseCase
import day2.RockPaperScissorsUseCase
import day3.RuckSacksUseCase

fun main() {

    // Day 1
    GetMaxCaloriesUseCase().getMaxCalories().also { println("max calories: $it") }
    GetMaxCaloriesUseCase().getTop3Count().also { println("Top 3 calories count: $it") }

    // Day 2
    RockPaperScissorsUseCase().getPointsForTheGuide().also { println("Points from following the guide: $it") }
    RockPaperScissorsUseCase().getPointsForTheGuideV2().also { println("Points from following the guide V2: $it") }

    // Day 3
    RuckSacksUseCase().getRuckSackPriorities().also { println("RuckSack priorities sum: $it") }
}