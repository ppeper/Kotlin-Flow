package channel.buffer

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * SUSPEND - 잠이 들었다 깨어난다.
 * DROP_OLDEST - 예전 데이터를 지운다.
 * DROP-LATEST - 새 데이터를 지운다.
 */
fun main() = runBlocking {
    val channel = Channel<Int>(2, BufferOverflow.DROP_OLDEST)
    launch {
        for (x in 1..50) {
            channel.send(x)
        }
        channel.close()
    }
    delay(500L)
    for (x in channel) {
        println("$x 수신")
        delay(100L)
    }
    println("완료")

}