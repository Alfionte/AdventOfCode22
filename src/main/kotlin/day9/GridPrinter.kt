@file:Suppress("unused")

package day9

import day9.model.RopeNode
import java.awt.Point

fun printResult(size: Int, points: HashSet<Point>) {
    for (rowColumn in size downTo -size) {
        for (rowIndex in -size..size) {
            if (points.contains(Point(rowIndex, rowColumn))) {
                if (rowIndex == 0 && rowColumn == 0) {
                    print("s")
                } else {
                    print("#")
                }
            } else {
                print(".")
            }
        }
        println()
    }
}

fun printIntermediate(size: Int, head: RopeNode.Head, points: List<RopeNode>) {
    for (columnIndex in size downTo -size) {
        for (rowIndex in -size..size) {
            val currentNode = RopeNode.Knot(Point(rowIndex, columnIndex))
            val current = points.firstOrNull { it == currentNode }

            if (current != null || head.position == currentNode.position) {
                when {
                    head.position == currentNode.position -> print("H")
                    current != null -> print(points.indexOf(current) + 1)
                }
            } else {
                if (currentNode.position == Point(0, 0)) {
                    print("s")
                } else {
                    print(".")
                }
            }
        }
        println()
    }
    println()
    repeat(30) { print("-") }
    println()
}