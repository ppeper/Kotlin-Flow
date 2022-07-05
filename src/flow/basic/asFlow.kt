package flow.basic

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // 1
    listOf(1, 2, 3, 4, 5).asFlow().collect { value ->
        println(value)
    }
    // 2
    (6..10).asFlow().collect { value ->
        println(value)
    }
    // flowOf
    flowOf(1, 2, 3, 4, 5).collect { value ->
        println(value)
    }
}