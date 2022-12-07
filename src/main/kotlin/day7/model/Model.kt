package day7.model


sealed class Element(open val parent: Dir?) {
    data class File(
        val name: String,
        val size: Int,
        override val parent: Dir? = null
    ) : Element(parent){
        override fun toString(): String = "File: $name"
    }

    data class Dir(
        val name: String = "/",
        val children: MutableList<Element> = mutableListOf(),
        override val parent: Dir? = null
    ) : Element(parent) {
        override fun toString(): String = "Dir: $name, children: $children"
    }
}

sealed class Command {
    object ChangeDirectoryTop : Command()
    object ChangeDirectoryUp : Command()
    data class ChangeDirectoryDown(val dirName: String) : Command()
    object DirectoryList : Command()
}