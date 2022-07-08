package flow.buffering

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun simple(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000L)
        emit(i)
    }
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().onEach {
            println("emit: $it")
        }.collect { value ->
            delay(3000L)
            println("collect: $value")
        }
    }
    print(time)
}
// Collected in 12063ms -> (1000 + 3000)만큼 delay * 3개 ~ 12000정도