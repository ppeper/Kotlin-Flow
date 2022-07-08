package flow.flattening

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500L)
    emit("$i: Second")
}

@FlowPreview
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100L) }
        .flatMapConcat {
            requestFlow(it)
        }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

/**
 * 1: First at 133 ms from start
 * 1: Second at 647 ms from start
 * 2: First at 752 ms from start
 * 2: Second at 1262 ms from start
 * 3: First at 1367 ms from start
 * 3: Second at 1879 ms from start
 */