package flow.operation

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val value = (1..10)
        .asFlow()
        .reduce { a, b ->
            a + b
        }
    println(value)
}