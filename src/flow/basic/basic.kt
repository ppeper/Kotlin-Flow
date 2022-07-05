package flow.basic

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

// 코틀린은 콜드 스트림으로 -> "collect"를 호출해야 값을 발생하기 시작
fun flowSomething(): Flow<Int> = flow {
    repeat(10) {
        emit(Random.nextInt(0, 500))
        delay(100L)
    }
}
fun main() = runBlocking {
    flowSomething().collect { value ->
        println(value)
    }
}