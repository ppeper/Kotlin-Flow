package flow.buffering

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun print(time: Long) {
    println("Collected in $time ms")
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        simple().onEach {
            println("emit: $it")
        }.buffer().collect { value ->
            delay(3000L)
            println("collect: $value")
        }
    }
    print(time)
}

/**
* emit: 1초일때 buffer을 통하여 collect할때 3초를 주어진다면
* emit은 1초후 기다리는것이아니라 계속 emit을 1, 2, 3을하고 3초가되면 collect를 하여 지연을 방지할 수 있다.
 * Collected in 10099 ms
*/

