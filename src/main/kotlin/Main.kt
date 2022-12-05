import day1.GetMaxCaloriesUseCase
import day2.RockPaperScissorsUseCase
import day3.RuckSacksUseCase
import day4.CleaningAreasUseCase
import day5.CraneUseCase


val useCases = listOf(
    GetMaxCaloriesUseCase(),
    RockPaperScissorsUseCase(),
    RuckSacksUseCase(),
    CleaningAreasUseCase(),
    CraneUseCase(),
)

fun main() {
    useCases.forEach { it.run() }
}