package day8

import day8.model.Tree
import domain.UseCase
import java.io.File

class TreeUseCase : UseCase {

    private val input = File("src/main/resources/day8/input.txt")

    private fun getTreeGrid(): MutableList<MutableList<Tree>> {
        val treeGreed = mutableListOf<MutableList<Tree>>()
        input
            .readLines()
            .forEach { line ->
                treeGreed.add(line.map { Tree(height = it.toString().toInt()) }.toMutableList())
            }
        return treeGreed
    }

    private fun List<MutableList<Tree>>.updateHorizontalVisibility() {
        val columnSize = this.size
        for (columnIndex in 0 until columnSize) {
            val rowLastIndex = this[columnIndex].lastIndex
            var highestLeftTree = -1
            var highestRightTree = -1

            for (treeIndex in 0..rowLastIndex) {

                // Left visibility
                if (this[columnIndex][treeIndex].height > highestLeftTree) {
                    this[columnIndex][treeIndex] =
                        this[columnIndex][treeIndex].copy(leftVisibility = true)
                    highestLeftTree = this[columnIndex][treeIndex].height
                }

                val rightTreeIndex = rowLastIndex - treeIndex
                // Right visibility
                if (this[columnIndex][rightTreeIndex].height > highestRightTree) {
                    this[columnIndex][rightTreeIndex] =
                        this[columnIndex][rightTreeIndex].copy(rightVisibility = true)
                    highestRightTree = this[columnIndex][rightTreeIndex].height
                }

            }
        }
    }

    private fun List<MutableList<Tree>>.updateVerticalVisibility() {
        val rowSize = this[0].size

        for (rowIndex in 0 until rowSize) {

            val columnLastIndex = this.lastIndex
            var highestTopTree = -1
            var highestBottomTree = -1

            for (columnIndex in 0..columnLastIndex) {

                // Top visibility
                if (this[columnIndex][rowIndex].height > highestTopTree) {
                    this[columnIndex][rowIndex] =
                        this[columnIndex][rowIndex].copy(topVisibility = true)
                    highestTopTree = this[columnIndex][rowIndex].height
                }

                val inverseColumnIndex = columnLastIndex - columnIndex
                // Bottom visibility
                if (this[inverseColumnIndex][rowIndex].height > highestBottomTree) {
                    this[inverseColumnIndex][rowIndex] =
                        this[inverseColumnIndex][rowIndex].copy(bottomVisibility = true)
                    highestBottomTree = this[inverseColumnIndex][rowIndex].height
                }

            }
        }
    }

    override fun run() {
        val treeGrid = getTreeGrid()

        treeGrid.updateHorizontalVisibility()
        treeGrid.updateVerticalVisibility()

        treeGrid
            .flatten()
            .count { it.rightVisibility || it.leftVisibility || it.topVisibility || it.bottomVisibility }
            .also { println("Visible trees: $it") }
    }
}