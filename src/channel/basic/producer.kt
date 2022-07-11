package channel.basic

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val producer = produce { // ProducerScope = CoroutineScope + SendChannel
        for (x in 1..10) {
            channel.send(x)
        }
    }
    producer.consumeEach {
        println(it)
    }
    println("완료")
}