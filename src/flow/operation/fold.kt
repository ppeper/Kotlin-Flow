package flow.operation

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
    val value = (1..10)
        .asFlow()
        .fold(10) { a, b ->
            a + b
        }
    println(value)
}