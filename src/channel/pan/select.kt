package channel.pan

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

// Return -> Received Channel
fun CoroutineScope.sayFast() = produce {
    // 코루틴 스코프 + Send Channel
    while (true) {
        delay(100L)
        send("패스트")
    }
}

// Return -> Received Channel
fun CoroutineScope.sayCampus() = produce {
    // 코루틴 스코프 + Send Channel
    while (true) {
        delay(150L)
        send("캠퍼스")
    }
}

fun main() = runBlocking {
    val fasts = sayFast()
    val campuses = sayCampus()
    repeat(5) {
        select { // 먼저 끝내는 애만 듣겠다 의미.
            fasts.onReceive {
                println("fast: $it")
            }
            campuses.onReceive {
                println("campus: $it")
            }
        }
    }
    coroutineContext.cancelChildren()
}