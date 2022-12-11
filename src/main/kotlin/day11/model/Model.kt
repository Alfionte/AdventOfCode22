package day11.model

data class Monkey(
    val monkeyNumber: Int,
    val items: MutableList<StolenItem>,
    val operation: ((old: Int) -> Int),
    val isTestPassed: (value: Int) -> Boolean,
    val testPassToMonkeyIndex: Int,
    val testFailToMonkeyIndex: Int,
)

data class StolenItem(val worryLevel: Int)

object MonkeyParser {

    private val monkeyNumberParser = "Monkey (\\d+):".toRegex()
    private val itemParser = "(\\d+)".toRegex()
    private val operationParser = "Operation: new = old ([+\\-*/]) (\\d+|old)".toRegex()
    private val testParser = "Test: divisible by (\\d+)".toRegex()
    private val testPassedParser = "If true: throw to monkey (\\d+)".toRegex()
    private val testFailedParser = "If false: throw to monkey (\\d+)".toRegex()

    fun List<String>.toMonkey(): Monkey {
        val monkeyNumberLine = this[0]
        val itemsLine = this[1]
        val operationLine = this[2]
        val testNumberLine = this[3]
        val testPassMonkeyLine = this[4]
        val testFailMonkeyLine = this[5]

        val monkeyNumber = monkeyNumberParser.find(monkeyNumberLine)!!.destructured.component1().toInt()
        val stolenItems = itemParser.findAll(itemsLine).map { StolenItem(it.value.toInt()) }.toMutableList()
        val operation = { old: Int ->
            operationParser
                .find(operationLine)!!
                .destructured
                .let { (operator, valueString) ->
                    val value = if (valueString == "old") old else valueString.toInt()
                    when (operator) {
                        "+" -> old + value
                        "-" -> old - value
                        "*" -> old * value
                        "/" -> old / value
                        else -> throw IllegalStateException("No operator recognized")
                    }
                }
        }
        val isTestPassed: (value: Int) -> Boolean = { value ->
            val testNumber = testParser
                .find(testNumberLine)!!
                .destructured
                .component1()
                .toInt()

            value % testNumber == 0
        }

        val testPassMonkeyIndex: Int = testPassedParser.find(testPassMonkeyLine)!!.destructured.component1().toInt()
        val testFailMonkeyIndex: Int = testFailedParser.find(testFailMonkeyLine)!!.destructured.component1().toInt()

        return Monkey(
            monkeyNumber = monkeyNumber,
            items = stolenItems,
            operation = operation,
            isTestPassed = isTestPassed,
            testPassToMonkeyIndex = testPassMonkeyIndex,
            testFailToMonkeyIndex = testFailMonkeyIndex,
        )
    }
}