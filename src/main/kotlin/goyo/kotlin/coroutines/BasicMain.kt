package goyo.kotlin.coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

// runBlocking은 코루틴을 실행하는 블로킹 함수입니다.
fun main(): Unit = runBlocking {
    printWithThread("START")
    // launch는 새로운 코루틴을 시작하는 빌더입니다.
    // launch는 반환값이 없는 코루틴을 만든다.
    launch {
        newRoutine()
    }
    yield() // 코루틴을 일시 중단하고 다른 코루틴을 실행할 수 있도록 한다.
    printWithThread("END")
}

// suspend 함수는 코루틴 내에서 호출되어야 하며, 다른 suspend 함수나 코루틴 빌더(예: launch, async) 내에서만 호출할 수 있다.
suspend fun newRoutine() {
    val num1 = 1
    val num2 = 2
    yield()
    printWithThread("${num1 + num2}")
}

fun printWithThread(str: Any) {
    println("$str - ${Thread.currentThread().name}")
}