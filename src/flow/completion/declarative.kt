package flow.completion

import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }
}