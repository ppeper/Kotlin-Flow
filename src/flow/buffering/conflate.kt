package flow.buffering

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().onEach {
            println("emit: $it")
        }.conflate().collect { value ->
            delay(3000L)
            println("collect: $value")
        }
    }
    print(time)
}
/**
 * conflate(뜻은 융합 -> 융합과는 뭔가 맞는 느낌은 아니다) 1초간 emit으로 데이터를 보내준다.
 * 첫번째 1을 collect 하게되면 3초간 delay가 생기고 그 이후에 2가 들어 왔을 거지만 delay가 3초 지난 이후에 다시 collect를 한다.
 * 따라서 2는 누락되고 3을 collect하게 된다
 * --------------------------------------
 * emit: 1
 * emit: 2
 * emit: 3
 * collect: 1
 * collect: 3
 * Collected in 7078 ms
 */