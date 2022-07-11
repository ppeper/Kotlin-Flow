package channel.basic

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *  가장 기본 구조는 한쪽에서 send -> 받는쪽에서 receive
 *  trySend, tryReceive도 사용한다.
 * 같은 코루틴에서 채널을 읽고 쓸수 없다.
 */
fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..10) {
            channel.send(x) // suspension point
        }
    }

    repeat(10) {
        println(channel.receive()) // suspension point
    }
    println("완료")
}