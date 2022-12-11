import day1.GetMaxCaloriesUseCase
import day10.VideoSignalUseCase
import day11.MonkeyBusinessUseCase
import day2.RockPaperScissorsUseCase
import day3.RuckSacksUseCase
import day4.CleaningAreasUseCase
import day5.CraneUseCase
import day6.SignalUseCase
import day7.BrowseFileSystemUseCase
import day8.TreeScenicScoreUseCase
import day8.TreeUseCase
import day9.LongRopeUseCase
import day9.RopeUseCase


val useCases = listOf(
    GetMaxCaloriesUseCase(),
    RockPaperScissorsUseCase(),
    RuckSacksUseCase(),
    CleaningAreasUseCase(),
    CraneUseCase(),
    SignalUseCase(),
    BrowseFileSystemUseCase(),
    TreeUseCase(),
    TreeScenicScoreUseCase(),
    RopeUseCase(),
    LongRopeUseCase(),
    VideoSignalUseCase(),
    MonkeyBusinessUseCase(),
)

fun main() {
    useCases.forEach { it.run() }
}