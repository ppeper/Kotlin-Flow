package channel.buffer

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 다른 설정값
 * UNLIMITED - 무제한으로 설정 -> 내부에선 LinkedList로 계속 연결하여 Memory가 부족하면 RunTimeException 발생
 * CONLATED - 오래된 값이 지워짐
 * BUFFERED - 64개의 버퍼. 오버플로우 발생 -> suspend
 */
fun main() = runBlocking {
    val channel = Channel<Int>(Channel.RENDEZVOUS) // buffer = 0
    launch {
        for (x in 1..20) {
            println("$x 전송중")
            channel.send(x)
        }
        channel.close()
    }

    for (x in channel) {
        println("$x 수신")
        delay(100L)
    }
    println("완료")
}