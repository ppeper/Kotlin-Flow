package flow.conjunction

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val nums = (1..3).asFlow()
    val strs = flowOf("일", "이", "삼")
    nums.zip(strs) { a, b ->
        "${a}은(는) $b"
    }.collect {
        println(it)
    }
}