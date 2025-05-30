package goyo.kotlin.Chapter09

/**
 * 9.1 제네릭스
 */

fun main() {
    // T는 타입 파라미터로, 제네릭 클래스나 함수에서 사용되는 타입을 나타낸다.
// 제네릭스는 타입 안전성을 제공하며, 다양한 타입에 대해 재사용 가능한 코드를 작성할 수 있게 해준다.
    class Box<T>(var value: T) {
        //fun getValue(): T = value
    }

    // 컴파일 오류: 코틀린에서는 raw 타입을 사용할 수 없음
    val box = Box("Hello") // 오류: 타입 인자가 필요함
//    box.value = 42 // 오류: 타입 불일치, Box<String>이므로 String만 허용됨

    // 올바른 사용법: 타입 인자 명시
    val box1 = Box<String>("Hello")
    val box2 = Box(42) // 타입 추론으로 Box<Int>로 결정됨
    println(box1.value) // Hello

    //자바는 타입 인자 생략 시 raw type으로 간주되며 타입 안정성이 무너질 수 있음
//    List authors = Arrays.asList("Dmitry", "Svetlana"); // raw type, 비추천

    /**
     * 	•	코틀린은 raw type을 아예 허용하지 않음
     * 	•	항상 제네릭 타입이 명확하게 지정되어 있어야 함
     * 	•	명시하지 않아도 타입 추론이 강력해서 안전하게 처리됨
     */
    val authors2 = listOf("Dmitry", "Svetlana") // List<String> 으로 추론됨

    /**
     * 제네릭 함수
     * 	•	제네릭 함수는 특정 타입이 아닌 여러 타입을 처리할 수 있도록 정의된 함수
     *	•	함수 선언 시 <T>와 같이 타입 파라미터를 선언하고, 이 T는 함수의 수신 객체 타입과 반환 타입에 쓰임
     */
    fun <T> List<T>.slice(indices: IntRange): List<T> {
        // this는 List<T>를 의미하며, indices에 해당하는 요소들을 반환
        return this.subList(indices.first, indices.last + 1)
    }

    // 제네릭 함수 사용 예시
    val letters = ('a'..'z').toList()

    println(letters.slice<Char>(0..2))  // <Char>타입 인자 명시: [a, b, c]
    println(letters.slice(10..13))      // 타입 추론 사용: [k, l, m, n]

    val numbers = (1..10).toList()

    println(numbers.slice<Int>(0..2)) // 타입 인자 명시
    println(numbers.slice(4..6))      // 타입 추론 → T = Int

    val words = listOf("apple", "banana", "cherry", "date", "fig")

    println(words.slice<String>(1..3)) // 명시
    println(words.slice(2..4))         // 추론 → T = String

    // 제네릭 고차 함수 filter
//    T는 리스트의 원소 타입을 나타냄
//    •	predicate는 (T) -> Boolean 형태의 함수 (고차 함수)
//    •	반환값도 List<T>
//    fun <T> List<T>.filter(predicate: (T) -> Boolean): List<T>

    val authors = listOf("Dmitry", "Svetlana")
    val readers = mutableListOf("Alice", "Bob", "Dmitry")

    val result = readers.filter { it !in authors } // [Alice, Bob]

    // 타입 파라미터 T 제약
    // T는 Number의 하위 타입이어야 함
    //	•	T : Number는 T가 Number의 하위 타입임을 명시 → 상한(upper bound)
    //	•	자바의 T extends Number와 동일한 개념
    fun <T : Number> oneHalf(value: T): Double {
        return value.toDouble() / 2.0
    }

    // 2. Comparable을 제약으로 사용한 max() 함수
    //	•	T : Comparable<T>는 T 타입이 자기 자신과 비교 가능해야 함을 뜻함
    fun <T : Comparable<T>> max(first: T, second: T): T {
        return if (first > second) first else second
    }
    // println(max("kotlin", 42)) // 오류: String과 Int는 비교할 수 없음

    //널이 될 수 없는 타입으로 타입 파라미터 제한
    //	•	제네릭 타입 T는 아무 제약도 없기 때문에 사실상 T : Any?와 같음.
    //	•	즉, T에 String?, Int?, null 등 널이 될 수 있는 타입이 들어올 수 있음.
    //	•	따라서 value.hashCode() 대신 value?.hashCode() 같이 안전한 호출을 강제해야 함.
//    class Processor<T> {
//        fun process(value: T) {
//            value?.hashCode()  // null-safe call 사용 필요
//        }
//    }

    //타입 파라미터를 널이 될 수 없는 타입으로 강제하고 싶다면 T : Any를 사용하라.
    class Processor<T : Any> {
        fun process(value: T) {
            value.hashCode()  // null-safe 호출 불필요
        }
    }
}

