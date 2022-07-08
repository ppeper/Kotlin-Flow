package flow.conjunction

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

// combine은 양쪽 데이터를 같은 시점에 묶는 것이 아닌 한 쪽이이 갱신되면 새로 묶는다.
fun main() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(100L) }
    val strs = flowOf("일", "이", "삼").onEach { delay(200L) }
    nums.combine(strs) { a, b ->
        "${a}은(는) $b"
    }.collect {
        println(it)
    }
}
