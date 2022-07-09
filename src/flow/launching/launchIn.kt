package flow.launching

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

fun log(msg: String) = println("${Thread.currentThread().name}: $msg ")

fun main() = runBlocking { // this: 코루틴 스코프, 코루틴.
    events()
        .onEach { event -> log("Event: $event") }
        .launchIn(this) // 코루틴 스코프. // 새로운 코루틴.
    log("Done")
    // 따라서 여기서 부터는 다른 작업가능
    // UI..
    // 네트워크 호출
}