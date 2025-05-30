package goyo.kotlin.Chapter09

import org.springframework.stereotype.Service
import java.util.*

/**
 * 9.2 실행 시 제네릭의 동작: 소거된 타입 파라미터와 실체화된 타입 파라미터
 */
fun main() {
    /**
     *  타입 소거(Type Erasure)
     * 	•	JVM에서는 제네릭 타입 정보가 컴파일 시점에만 존재하고, 런타임에서는 소거됨
     * 	•	예: List<String>도, List<Int>도 런타임에서는 똑같이 List로 인식됨
     * 	•	이 때문에 런타임에는 타입 파라미터를 기준으로 타입 검사 또는 캐스트가 불가능
     */

    // 런타임 시에는 List<String>과 List<Int> 모두 List로 취급됨
    val list1: List<String> = listOf("a", "b")
    val list2: List<Int> = listOf(1, 2, 3)

    /**
     * 	•	JVM에서는 List<String> 이든 List<Int> 이든 모두 List로 소거됨.
     * 	•	즉, value is List<String> 같은 코드는 런타임 시점엔 검사 불가능.
     */
//    fun printIfStringList(value: Any) {
//        if (value is List<String>) {  //  컴파일 오류! Cannot check for instance of erased type: List<String>
//            println("문자열 리스트입니다: $value")
//        }
//    }

    fun printSum(c: Collection<*>) {
        val intList = c as? List<Int>
            ?: throw IllegalArgumentException("List is expected")
        println(intList.sum())
    }

    printSum(listOf(1, 2, 3))  // 6 출력 정상작동
//    printSum(setOf(1, 2, 3))  // IllegalArgumentException: List is expected, 단순히 list가 아닌것을 체크함
//    printSum(listOf("a", "b", "c"))  //  java.lang.ClassCastException: class java.lang.String cannot be cast to class java.lang.Number

    // 알려진 타입 인자를 사용해 타입 검사하기는됨.
    fun printSum2(c: Collection<Int>) {
        if (c is List<Int>) {
            println(c.sum()) //  명시적으로 타입이 확정됨
        }
    }

    printSum2(listOf(1, 2, 3))  // 6 출력 정상작동
    printSum2(setOf(1, 2, 3))  // 6 출력 정상작동
//    printSum2(listOf("a", "b", "c"))  //  Type mismatch: inferred type is List<String> but Collection<Int> was expected

    /**
     * 9.2.2 실체화된 타입 파라미터를 사용한 함수 선언
     * 코틀린에서는 실체화된 타입 파라미터를 사용하여 런타임에 타입 정보를 유지할 수 있다.
     */

//    fun <T> isA(value: Any) = value is T //Cannot check for instance of erased type 'T (of fun <T> isA)'.

    println(isA<String>("abc"))  // true
    println(isA<String>(123))    // false

    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>()) // [one, three]

//    inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> {
//        val destination = mutableListOf<T>()
//        for (element in this) {
//            if (element is T) {
//                destination.add(element)
//            }
//        }
//        return destination
//    }

    /**
     * 9.2.3 실체화된 타입 파라미터로 클래스 참조 대체
     */

    val serviceImpl = loadService<Service>()

    /**
     * 9.2.4 실체화된 타입 파라미터의 제약
     *   1. 타입 검사 및 캐스팅 is, !is, as 연산자 사용 가능
     */
//   2. ::class 참조
//    fun <T> printClass() {
//        println(T::class.java) // 오류: Cannot use 'T::class' as reified type parameter
//    }
//
//    inline fun <reified T> printClass() {
//        println(T::class.java)
//    }
//
//    printClass<String>()   // 출력: class java.lang.String
}

// 해결책: inline + reified 사용
//	•	inline: 함수 호출 시 코드가 인라인으로 삽입되어 컴파일됨, 호출되는 곳에 코드가 인라인 삽입됨 → 타입 정보도 그대로 유지 가능
//	•	reified: 제네릭 타입 정보를 런타임에 유지함 → is T 같은 검사 가능

// 추가 설명
//	•	reified는 반드시 inline 함수 안에서만 사용 가능
//	•	그 이유는 컴파일 타임에 타입을 알 수 있게 코드에 “하드코딩”되기 때문
//	•	일반 함수나 클래스 레벨에서는 타입 파라미터는 런타임에 소거(type erasure) 되므로 사용할 수 없음

//❗ 주의 사항
//•	reified는 반드시 inline 함수 안에서만 사용 가능
//•	inline이 아니면 Kotlin 컴파일러는 T의 타입 정보를 유지할 수 없음
//•	reified의 핵심은: “타입 파라미터를 런타임에 사용할 수 있게 만든다”
inline fun <reified T> isA(value: Any) = value is T

inline fun <reified T> loadService(): ServiceLoader<T> {
    return ServiceLoader.load(T::class.java)
}