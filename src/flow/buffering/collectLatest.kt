package flow.buffering

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().onEach {
            println("emit: $it")
        }.collectLatest { value ->
            println("collect: $value")
            delay(3000L)
        }
    }
    print(time)
}
/**
 * collectLatest는 수집이 느릴 경우 새로운 데이터가 있을때 수집 측을 종료시키고 새로 시작한다.
 * 1이 들어온 후 collect를 한 후 3초간의 delay를 걸었지만 새로운 데이터인 2가 다시 발행되어 2초간의 delay가 없이 작업이 완료된다.
 * emit: 1
 * collect: 1
 * emit: 2
 * collect: 2
 * emit: 3
 * collect: 3
 * Collected in 6080 ms
 * -----한계점------
 * delay를 println()위로 이동을 하게되면 1 데이터가 들어온 후 delay 3초로 인하여 새로 들어온 데이터는 계속 취소가된다.
 * 따라서 1, 2의 중간에 들어온 값들은 하나도 얻지 못하고 마지막 3 데이터만을 얻는 경우가 있을 수 있다.
 */