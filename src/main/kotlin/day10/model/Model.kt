@file:Suppress("SpellCheckingInspection")

package day10.model

sealed interface Instruction {
    object Noop : Instruction {
        override fun toString() = "Noop"
    }

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


sealed interface Pixel {
    object Lit : Pixel {
        override fun toString() = "#"
    }

    object Dark : Pixel {
        override fun toString() = "."
    }
}

data class DrawResult(val registy : Int, val pixels : List<Pixel>)