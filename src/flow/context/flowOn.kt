package flow.context

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//fun simple2(): Flow<Int> = flow {
//    withContext(Dispatchers.Default) { // flow 내에서는 context를 바꿀수 없다. -> 여기서 오류
//        for (i in 1..10) {
//            delay(100L)
//            emit(1)
//        }
//    }
//}
//
//fun main() = runBlocking<Unit> {
//    launch(Dispatchers.IO) {
//        simple2()
//            .collect { value ->
//                log("${value}를 받음.")
//            }
//    }
//}

// flowOn 연산자로 context 바꿀수 있다.
fun simple3(): Flow<Int> = flow {
    for (i in 1..10) {
        delay(100L)
        log("값 ${i}를 emit 합니다.")
        emit(i)
    } // 업스트림만 Dispatchers.Default
}.flowOn(Dispatchers.Default) // 위치

fun main() = runBlocking {
    simple3().collect { value -> // 다운스트림
        log("${value}를 받음.")
    }
}