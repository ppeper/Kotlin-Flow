package channel.pipeline

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.produceStringNumbers(numbers: ReceiveChannel<Int>): ReceiveChannel<String> = produce {
    for (i in numbers) {
        send("${i}!")
    }
}


fun main() = runBlocking {
    // 하나의 스트림을 프로듀서가 만든다.
    val numbers = produceNumbers()
    // 다른 코루틴에서 그 스트림을 읽어 새로운 스트림을 만드는 패턴 (일반적인 패턴: 파이프라인)
    val stringNumbers = produceStringNumbers(numbers)

    repeat(5) {
        println(stringNumbers.receive())
    }
    println("완료")
    coroutineContext.cancelChildren()
}