import day1.GetMaxCaloriesUseCase
import day2.RockPaperScissorsUseCase
import day3.RuckSacksUseCase
import day4.CleaningAreasUseCase

fun main() {

    // Day 1
    GetMaxCaloriesUseCase().getMaxCalories().also { println("max calories: $it") }
    GetMaxCaloriesUseCase().getTop3Count().also { println("Top 3 calories count: $it") }
    println()

    // Day 2
    RockPaperScissorsUseCase().getPointsForTheGuide().also { println("Points from following the guide: $it") }
    RockPaperScissorsUseCase().getPointsForTheGuideV2().also { println("Points from following the guide V2: $it") }
    println()

    // Day 3
    RuckSacksUseCase().getRuckSackPriorities().also { println("RuckSack priorities sum: $it") }
    RuckSacksUseCase().getBadgePriorities().also { println("Badge priorities sum: $it") }
    println()

    // Day 4
    CleaningAreasUseCase().getTotalOverlapAssignments().also { println("Total overlapping assignments: $it") }
    CleaningAreasUseCase().getPartialOverlapAssignments().also { println("Partial overlapping assignments: $it") }
    println()
}