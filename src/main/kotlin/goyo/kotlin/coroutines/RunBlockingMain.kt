package goyo.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // runBlocking은 코루틴을 실행하는 블로킹 함수로, 현재 스레드를 차단하고 코루틴이 완료될 때까지 기다린다.
    runBlocking {
        printWithThread("START")
        // 코루틴을 실행하는 블로킹 함수
        launch {
            // delay는 코루틴을 일시 중단하는 함수로, 다른 코루틴이 실행될 수 있도록 한다.
            delay(2_000L) // 나를 특정 시간만큼 멈추고 다른 코루틴으로 넘김
            printWithThread("LAUNCH END")
        }
    }


    printWithThread("END")
}

