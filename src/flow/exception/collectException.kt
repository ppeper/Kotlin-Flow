package flow.exception

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}
// 수집에서 try-catch 처리
fun main() = runBlocking {
    try {
        simple().collect { value ->
            println("collect: $value")
            check(value <= 1) {
                "In Collected $value"
            }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }

}
