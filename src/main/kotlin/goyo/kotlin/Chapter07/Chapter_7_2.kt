package goyo.kotlin.Chapter07

/**
 * 7.2 비교 연산자 오버로딩
 * 코틀린에서는 모든 객체에 대해 비교 연산을 수행할 수 있다.
 * equals() 메서드와 compareTo() 메서드를 오버로딩하여 비교 연산을 지원한다.
 */

fun main() {
    // 7.2.1 동등성 연산자: equals()
    class Point(val x: Int, val y: Int) {
        override fun equals(obj: Any?): Boolean {
            // 파라미터가 자기 자신과 같으면 true, === 연산자는 두 피연산자가 더로 같은 객체를 가리키는지 비교한다.
            // equals() 구현할떄 === 를 사용해 자기 자신과의 비교를 최적화한다.
            // === 를 오버로딩 할 수 없다.
            if (obj === this) return true
            if (obj !is Point) return false
            return x == obj.x && y == obj.y
        }
    }

    // ==, != 연산자는 equals() 메서드를 호출한다.
    // a가 null이면 b도 null인 경우에만 결과가 true로 나온다.
    // a == b -> a?.equals(b) ?: b == null
    println(Point(10, 20) == Point(10, 20)) // true
    println(Point(10, 20) != Point(5, 5)) // true
    println(null == Point(1, 2))

    // 7.2.2 순서 연산자: compareTo()
    // p1 < p2 -> p1.compareTo(p2) < 0
    class Person(
        val firstName: String,
        val lastName: String,
    ) : Comparable<Person> {
        override fun compareTo(other: Person): Int {
            // compareTo() 메서드는 음수, 0, 양수를 반환한다.
            // 음수는 this가 other보다 작다는 의미
            // 0은 this가 other와 같다는 의미
            // 양수는 this가 other보다 크다는 의미
            return if (lastName == other.lastName) {
                firstName.compareTo(other.firstName)
            } else {
                lastName.compareTo(other.lastName)
            }
        }
    }
}