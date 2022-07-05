package flow.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.random.Random

fun flowSomethingCancel(): Flow<Int> = flow {
    repeat(10) {
        emit(Random.nextInt(0, 500))
        delay(100L)
    }
}

fun main() = runBlocking {
    val result = withTimeoutOrNull(500L) {
        flowSomethingCancel().collect { value ->
            println(value)
        }
        true
    } ?: false
    if (!result) {
        println("취소되었습니다.")
    }
}