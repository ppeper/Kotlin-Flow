package flow.exception

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    simple()
        .catch { e -> println("Caught $e") } // does not catch downstream exceptions
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}