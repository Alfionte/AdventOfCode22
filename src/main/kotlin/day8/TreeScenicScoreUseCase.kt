package day8

import day8.model.ScenicTree
import domain.UseCase
import java.io.File

class TreeScenicScoreUseCase : UseCase {

    private val input = File("src/main/resources/day8/input.txt")

    private fun getScenicTreeGrid(): MutableList<MutableList<ScenicTree>> {
        val treeGreed = mutableListOf<MutableList<ScenicTree>>()
        input
            .readLines()
            .forEach { line ->
                treeGreed.add(line.map { ScenicTree(height = it.toString().toInt()) }.toMutableList())
            }
        return treeGreed
    }


    private fun List<MutableList<ScenicTree>>.updateHorizontalVisibility() {
        for (columnIndex in 0 until lastIndex) {
            for (rowIndex in 0..this[0].lastIndex) {

                val currentTree = this[columnIndex][rowIndex]

                // Left
                var leftIndex = rowIndex - 1
                var leftTree = this[columnIndex].getOrNull(leftIndex)
                var countLeftTrees = 0

                while (leftTree != null) {
                    countLeftTrees++
                    leftIndex--
                    if(leftTree.height >= currentTree.height) break
                    leftTree = this[columnIndex].getOrNull(leftIndex)
                }

                // Right
                var rightIndex = rowIndex + 1
                var rightTree = this[columnIndex].getOrNull(rightIndex)
                var countRightTrees = 0

                while (rightTree != null) {
                    countRightTrees++
                    rightIndex++
                    if(rightTree.height >= currentTree.height) break
                    rightTree = this[columnIndex].getOrNull(rightIndex)
                }

                // Top
                var topIndex = columnIndex - 1
                var topTree = this.getOrNull(topIndex)?.get(rowIndex)
                var countTopTrees = 0

                while (topTree != null) {
                    countTopTrees++
                    topIndex--
                    if(topTree.height >= currentTree.height) break
                    topTree = this.getOrNull(topIndex)?.get(rowIndex)
                }

                // Bottom
                var bottomIndex = columnIndex + 1
                var bottomTree = this.getOrNull(bottomIndex)?.get(rowIndex)
                var countBottomTrees = 0

                while (bottomTree != null) {
                    countBottomTrees++
                    bottomIndex++
                    if(bottomTree.height >= currentTree.height) break
                    bottomTree = this.getOrNull(bottomIndex)?.get(rowIndex)
                }

                this[columnIndex][rowIndex] = currentTree.copy(
                    leftVisibility = countLeftTrees,
                    rightVisibility = countRightTrees,
                    topVisibility = countTopTrees,
                    bottomVisibility = countBottomTrees
                )
            }
        }
    }


    override fun run() {
        val treeGrid = getScenicTreeGrid()

        treeGrid.updateHorizontalVisibility()

        treeGrid
            .flatten()
            .map { it.scenicScore }
            .max()
            .also { println("Max scenic score: $it") }
        println()
    }
}