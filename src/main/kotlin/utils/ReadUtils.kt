package utils

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import java.io.File

class ReadUtils(
    private val input: File
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun getLineStream(): Flow<String> =
        flow {
            input.forEachLine { line ->
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        ensureActive()
                        emit(line)
                    }
                }
            }
        }

    fun getBlocksStream() =
        getLineStream().transform {
            var currentList = mutableListOf<String>()
            if (it.isNotEmpty()) {
                currentList.add(it)
            } else {
                emit(currentList).also { currentList = mutableListOf() }
            }
        }
}