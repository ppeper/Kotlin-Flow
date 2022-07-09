package flow.completion

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

fun simple2(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

fun main() = runBlocking {
    simple2()
        .onCompletion { cause ->
            if (cause != null) {
                println("Flow completed exceptionally")
            } else {
                println("Flow completed")
            }
        }
        .catch { cause -> println("Caught exception")  }
        .collect { value -> println(value) }
}