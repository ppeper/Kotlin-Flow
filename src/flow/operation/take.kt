package flow.operation

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc(it))
    }.take(5)
        .collect {
            println(it)
        }
}