package day8.model

data class Tree(
    val height: Int = 0,
    val leftVisibility: Boolean = false,
    val rightVisibility: Boolean = false,
    val topVisibility: Boolean = false,
    val bottomVisibility: Boolean = false,
)

data class ScenicTree(
    val height: Int = 0,
    val leftVisibility: Int = 0,
    val rightVisibility: Int = 0,
    val topVisibility: Int = 0,
    val bottomVisibility: Int = 0,
) {
    val scenicScore
        get() = leftVisibility * rightVisibility * topVisibility * bottomVisibility
}