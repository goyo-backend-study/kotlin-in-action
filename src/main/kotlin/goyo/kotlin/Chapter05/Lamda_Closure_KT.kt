package goyo.kotlin.Chapter05

import java.util.function.Consumer

class Lamda_Closure_KT {

}

fun main(args: Array<String>) {
    val strings: MutableCollection<String?> = mutableListOf<String?>("4", "5", "4", "5", "4", "5")

    var count = 0

    strings.forEach(Consumer { string: String? ->
        if (string!!.startsWith("4")) {
            count++ //  Kotlin은 람다에서 var 변수를 캡처(Closure)할 수 있음
        }
    })
    println(count)
}