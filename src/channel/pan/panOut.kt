package channel.pan

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 팬 아웃은 소비자가 많은 경우이다.
fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        send(x++)
        delay(100L)
    }
}

fun CoroutineScope.processNumber(id: Int, channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("${id}가 ${it}을 받았습니다.")
    }
}

fun main() = runBlocking {
    val producer = produceNumbers()
    repeat(5) {
        processNumber(it, producer)
    }
    delay(1000L)
    producer.cancel()
}