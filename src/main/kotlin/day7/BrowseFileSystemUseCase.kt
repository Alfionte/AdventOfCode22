package day7

import day7.model.*
import domain.UseCase
import java.io.File

class BrowseFileSystemUseCase : UseCase {

    private val input = File("src/main/resources/day7/input.txt")

    private fun readFileSystem(): Element.Dir {
        var currentCommand: Command? = null
        val root = Element.Dir()
        var currentDir = root

        input
            .readLines()
            .forEach { line ->
                if (isCommand(line)) {
                    val lineCommand = getCommand(line)
                    when (lineCommand) {
                        is Command.ChangeDirectoryDown -> {
                            currentDir = currentDir
                                .children
                                .filterIsInstance<Element.Dir>()
                                .first { it.name == lineCommand.dirName }
                        }

                        Command.ChangeDirectoryTop -> currentDir = root
                        Command.ChangeDirectoryUp -> currentDir.parent?.let { currentDir = it }
                        Command.DirectoryList -> Unit
                    }.also {
                        currentCommand = lineCommand
                    }
                } else {
                    (currentCommand as Command.DirectoryList).let {
                        val dir = getDir(line, currentDir)
                        val file = getFile(line, currentDir)
                        when {
                            dir != null -> currentDir.children.add(dir)
                            file != null -> currentDir.children.add(file)
                            else -> Unit
                        }
                    }
                }
            }

        return root
    }

    override fun run() {
        readFileSystem().also { println("Read file system: $it") }
        println()
    }
}