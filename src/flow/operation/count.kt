package flow.operation

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val counter = (1..10)
        .asFlow()
        .count { // 종단 연산자, terminal operator, 특정 값, 컬렉션, 결과.
            (it % 2) == 0
        }
    println(counter)
}