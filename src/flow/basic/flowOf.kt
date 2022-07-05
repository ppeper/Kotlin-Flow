package flow.basic

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flowOf(1, 2, 3, 4, 5).collect { value ->
        println(value)
    }
    // flow 로 5개를 가져오는 같은 코드
    flow {
        emit(10)
        emit(9)
        emit(8)
        emit(7)
        emit(6)
    }.collect { value ->
        println(value)
    }
}