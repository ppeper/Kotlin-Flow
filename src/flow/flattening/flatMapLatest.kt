package flow.flattening

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100L) }
        .flatMapLatest {
            requestFlow(it)
        }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}

/**
 * 1: First at 169 ms from start
 * 2: First at 279 ms from start
 * 3: First at 382 ms from start
 * 3: Second at 893 ms from start
 */