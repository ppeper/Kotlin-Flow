package flow.exception

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.runBlocking

/**
 * 빌더 코드 블록 내에게 예외 처리하는 것은 예외 투명성을 어기는 것이다.
 * 플로우에서는 "catch 연산자" 를 이용하는 것을 권한다.
 */
fun main() = runBlocking {
    simpleAnyWhere()
        .catch { e -> emit("Caught $e") }
        .collect { value -> println("collection $value") }
}