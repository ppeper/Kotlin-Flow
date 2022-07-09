package flow.launching

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// addEventListener 대신에 onEach를 사용이 가능하다.
fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100L) }

fun main() = runBlocking {
    events()
        .onEach { event -> println("Event: $event") }
        .collect() // 스트림이 끝날 때 까지 기다리게 된다. 이벤트 -> 계속 발생
    println("Done")
    // UI..
    // 네트워크 호출
}
// collect()는 이벤트 리스닝을 할 수 없다.
