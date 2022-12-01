import day1.GetMaxCaloriesUseCase

fun main() {
    val maxCaloriesUseCase = GetMaxCaloriesUseCase()
    maxCaloriesUseCase.getMaxCalories().also { println("max calories: $it") }
    maxCaloriesUseCase.getTop3Count().also { println("Top 3 calories count: $it") }
}