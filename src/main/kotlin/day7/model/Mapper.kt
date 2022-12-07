package day7.model


private val fileRegex = "(\\d+) (\\S+)".toRegex()
private val dirRegex = "dir (\\S+)".toRegex()
private val changeDirTopRegex = "\\$ cd /".toRegex()
private val changeDirUpRegex = "\\$ cd \\.\\.".toRegex()
private val changeDirDownRegex = "\\$ cd (\\S+)".toRegex()
private val directoryListRegex = "\\$ ls".toRegex()

fun getFile(value: String, parent: Element.Dir?): Element.File? =
    fileRegex
        .find(value)
        ?.destructured
        ?.let { (size, name) ->
            Element.File(name, size.toInt(), parent = parent)
        }

fun getDir(value: String, parent: Element.Dir?): Element.Dir? =
    dirRegex
        .find(value)
        ?.destructured
        ?.let { (dirName) ->
            Element.Dir(dirName, parent = parent)
        }

fun isCommand(value: String): Boolean = value.startsWith("$")

fun getCommand(value: String): Command {
    val changeDirTop = changeDirTopRegex.find(value)
    val changeDirUp = changeDirUpRegex.find(value)
    val changeDirDown = changeDirDownRegex.find(value)
    val directoryList = directoryListRegex.find(value)

    return when {
        changeDirTop != null -> Command.ChangeDirectoryTop
        changeDirUp != null -> Command.ChangeDirectoryUp
        changeDirDown != null -> Command.ChangeDirectoryDown(changeDirDown.groupValues[1])
        directoryList != null -> Command.DirectoryList
        else -> throw IllegalStateException("No command recognized: $value")
    }
}