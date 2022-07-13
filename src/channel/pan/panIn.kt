package channel.pan

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

suspend fun produceNumbers(channel: SendChannel<Int>, from: Int, interval: Long) {
    var x = from
    while (true) {
        channel.send(x)
        x += 2
        delay(interval)
    }
}

fun CoroutineScope.processNumber(channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("${it}을 받았습니다.")
    }
}

// 팬 인은 생산자가 많은 경우이다.
fun main() = runBlocking {
    val channel = Channel<Int>() // Channel = Receive Channel (receive) + Send Channel (send)이다
    launch {
        produceNumbers(channel, 1, 100L) // 1, 3, 5, 7, 9 // 100ms
    }
    launch {
        produceNumbers(channel, 2, 150L) // 2, 4, 6, 8, 10 // 150ms
    } // 생산자 2, 소비자 1
    processNumber(channel) // 여기서 소비
    delay(1000L)
    coroutineContext.cancelChildren()
}
