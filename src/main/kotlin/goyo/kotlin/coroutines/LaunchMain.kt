package goyo.kotlin.coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    // launch는 새로운 코루틴을 시작하는 빌더로, 반환값이 없는 코루틴을 만든다.
    // launch는 바로 쓸수 없고, runBlocking 내에서 사용해야 한다. 코루틴 세계로 이어져야 한다.
    // start 파라미터를 CoroutineStart.LAZY로 설정하면 코루틴이 즉시 시작되지 않고, 명시적으로 start() 메서드를 호출해야 실행된다.
    val job = launch(start = CoroutineStart.LAZY) {
        printWithThread("launch start")
    }

    printWithThread("Hello, World!")
    delay(1000L)
    job.start()
}