package flow.flattening

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

@FlowPreview
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100L) }
        .flatMapMerge {
            requestFlow(it)
        }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}
/**
 * 1: First at 164 ms from start
 * 2: First at 271 ms from start
 * 3: First at 376 ms from start
 * 1: Second at 675 ms from start
 * 2: Second at 780 ms from start
 * 3: Second at 886 ms from start
*/
