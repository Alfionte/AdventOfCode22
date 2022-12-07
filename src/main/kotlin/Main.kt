import day1.GetMaxCaloriesUseCase
import day2.RockPaperScissorsUseCase
import day3.RuckSacksUseCase
import day4.CleaningAreasUseCase
import day5.CraneUseCase
import day6.SignalUseCase
import day7.BrowseFileSystemUseCase


val useCases = listOf(
    GetMaxCaloriesUseCase(),
    RockPaperScissorsUseCase(),
    RuckSacksUseCase(),
    CleaningAreasUseCase(),
    CraneUseCase(),
    SignalUseCase(),
    BrowseFileSystemUseCase(),
)

fun main() {
    useCases.forEach { it.run() }
}