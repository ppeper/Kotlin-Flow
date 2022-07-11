package channel.basic

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch {
        for (x in 1..10) {
            channel.send(x)
        }
        channel.close()
    }
    // close를 통하여 언제 종료될지를 알 수 있음 -> for문으로 사용 가능
    for (x in channel) {
        println(x)
    }
    println("완료")
}

