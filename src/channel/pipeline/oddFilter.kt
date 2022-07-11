package channel.pipeline

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.filterOdd(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    for (i in numbers) {
        if (i % 2 == 1) {
            send("${i}!")
        }
    }
}

fun main() = runBlocking {
    val numbers = produceNumbers()
    val oddNumbers = filterOdd(numbers)

    repeat(10) {
        println(oddNumbers.receive())
    }
    println("완료")
    coroutineContext.cancelChildren()
}
