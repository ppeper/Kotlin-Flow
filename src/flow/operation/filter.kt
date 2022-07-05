package flow.operation

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    // Filter
    (1..20).asFlow().filter {
        (it % 2) == 0 // 술어 -> predicate
    }.collect {
        println(it)
    }
    // FilterNot
    (1..20).asFlow().filterNot {
        (it % 2) == 0
    }.collect {
        println(it)
    }
}