package flow.context

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun simple(): Flow<Int> = flow {
    log("Flow 시작")
    for (i in 1..10) {
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO) {
        simple()
            .collect { value ->
                log("${value}를 받음.")
            }
    }
}