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
        /**
         * compareTo() 메서드는 두 객체를 비교하는 메서드로, 음수, 0, 양수를 반환한다.
         * 음수: 현재 객체가 비교 대상보다 작음.
         * 0: 현재 객체와 비교 대상이 같음.
         * 양수: 현재 객체가 비교 대상보다 큼.
         */
//        override fun compareTo(other: Person): Int {
//            return if (lastName == other.lastName) {
//                firstName.compareTo(other.firstName)
//            } else {
//                lastName.compareTo(other.lastName)
//            }
//        }

        /**
         * 2번째 메서드는 this와 other 객체를 비교
         * , 비교 순서는 lastName을 먼저 비교하고, lastName이 같으면 firstName을 비교
         */
        override fun compareTo(other: Person): Int {
            return compareValuesBy(
                this, other,
                Person::lastName, Person::firstName
            )
        }
    }

    val p1 = Person("Alice", "Smith")
    val p2 = Person("Bob", "Johnson")
    println(p1 < p2) // false

    /**
     * Tip: 처음에는 성능에 신경쓰지말고 이해하기 쉽고 간결하게 코드를 작성하고, 나중에 그 코드가 자주 호출됨에 따라 성능이 문제가 되면 성능을 개선하라
     */
}