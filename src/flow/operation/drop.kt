package flow.operation

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    (1..20).asFlow().transform {
        emit(it)
        emit(someCalc(it))
    }.drop(5)
        .collect {
            println(it)
        }
}