package flow.exception

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

// 어느 곳에서 발생한 예외라도 처리가 가능하다.
fun simpleAnyWhere(): Flow<String> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}.map { value ->
    check(value <= 1) {
        "Crashed on $value"
    }
    "string $value"
}

fun main() = runBlocking {
    try {
        simpleAnyWhere().collect { value ->
            println(value)
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}