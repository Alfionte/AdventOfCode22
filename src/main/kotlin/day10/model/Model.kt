@file:Suppress("SpellCheckingInspection")

package day10.model

sealed interface Instruction {
    object Noop : Instruction /* no-op */
    data class AddX(val value: Int) : Instruction
}


private const val NOOP = "noop"
private const val ADD_X_REGEX = "addx (-?\\d+)"
fun String.toInstruction(): Instruction =
    if (this == NOOP) {
        Instruction.Noop
    } else {
        ADD_X_REGEX
            .toRegex()
            .find(this)!!
            .destructured
            .let { (number) -> Instruction.AddX(number.toInt()) }
    }