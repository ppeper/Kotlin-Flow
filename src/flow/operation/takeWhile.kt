package flow.operation

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc(it))
    }.takeWhile {
        it < 15
    }.collect {
        println(it)
    }
}