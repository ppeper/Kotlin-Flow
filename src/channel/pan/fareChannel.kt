package channel.pan

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 공평하게 돌아가면서 한칸씩 문자열이 마지막에 붙어서 receive한다.
suspend fun someone(channel: Channel<String>, name: String) {
    for (comment in channel) {
        println("${name}: $comment")
        channel.send(comment.drop(1) + comment.first())
        delay(100L)
    }
}

fun main() = runBlocking {
    val channel = Channel<String>()
    launch {
        someone(channel, "코트린")
    }
    launch {
        someone(channel, "자바")
    }
    channel.send("코루틴채널")
    delay(1000L)
    coroutineContext.cancelChildren()
}