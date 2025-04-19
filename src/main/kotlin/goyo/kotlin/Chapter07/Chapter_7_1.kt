package goyo.kotlin.Chapter07

import java.math.BigDecimal

/**
 * 7.1 연산자 오버로딩
 *  - 코틀린은 연산자 오버로딩을 지원한다.
 *
 *  자바에서는 원시타입에 대해서만 산술 연산자을 지원한다.
 *  코틀린은 클래스 타입에 대해서도 산술 연산자를 지원한다.
 *   - BigInteger, BigDecimal 등의 연산자 오버로딩을 지원한다.
 */
fun main() {
    data class Point(var x: Int, var y: Int) {
        /**
         * 코틀린에서는 프로그래머가 직접 연산자를 만들어 사용할 수 없고, 언어에서 미리 정해준 연산자만 오버로딩 할수 있으며
         * 이름이 연산자별로 정해져있다.
         */
        operator fun plus(other: Point) = Point(x + other.x, y + other.y)
        operator fun minus(other: Point) = Point(x - other.x, y - other.y)
        operator fun times(other: Point) = Point(x * other.x, y * other.y)
        operator fun div(other: Point) = Point(x / other.x, y / other.y)
        operator fun rem(other: Point) = Point(x % other.x, y % other.y)

        // 연산자를 정의할 때 두 피연산자가 같은 타입일 필요는 없다.
        operator fun times(scale: Double) = Point((x * scale).toInt(), (y * scale).toInt())

        // 복합 연산자 오버로딩
        operator fun plusAssign(other: Point) {
            x += other.x
            y += other.y
        }

        // 단항 연산자 오버로딩
        operator fun unaryMinus() = Point(-x, -y) // 각 성분의 음수를 취한 새 Point를 반환한다.
    }

    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2) // Point(x=40, y=60)
    println(p1 * p2) // Point(x=300, y=800)
    println(p1 - p2) // Point(x=-20, y=-20)

    // 자바의 BigInteger는 연산자 오버로딩을 지원하지 않는다.
    val bigInteger = java.math.BigInteger("345")
    val bigInteger2 = java.math.BigInteger("123")
    println(bigInteger + bigInteger2) // 468, 자바는 + 연산 불가능

    // 연산자를 정의할 때 두 피연산자가 같은 타입일 필요는 없다.
    println(p1 * 1.5) // Point(x=15, y=30)

    // 교환법칙은 지원하지 않으며, 새로운 함수를 만들어야 한다.
//    operator fun Double.times(point: Point): Point {
//        return Point((point.x * this).toInt(), (point.y * this).toInt())
//    }
//    println(1.5 * p1)

    // 복합 대입 연산자
    p1 += p2 // plusAssign() 연산자 오버로딩
    println(p1) // Point(x=40, y=60)

    // 단항 연산자
    println(-p1) // Point(x=-40, y=-60)

    /**
     * !a = not
     * ++a, a++ = inc
     * --a, a-- = dec
     */
    var bd = BigDecimal.ZERO
    println(++bd) // 1
    operator fun BigDecimal.inc(): BigDecimal {
        return this + BigDecimal.TEN // 10을 더한 값을 반환한다.
    }
    println(++bd) // 11

    // 코틀린 표준 라이브러리는 변경 가능한 컬렉션에 대해서 plusAssign() 연산자를 정의하고 있다.
    val numbers = ArrayList<Int>()
    numbers += 1 // plusAssign() 연산자 오버로딩, add() 메서드 호출
    println(numbers)

    /**
     * TIP
     * += 를 plus, plusAssign 양쪽으로 컴파일 가능하다. 그러나 클래스를 일관성있게 설계하는게 좋다.
     * plus와 plusAssign 연산을 동시에 정의하지 말자
     * 변경 불가한 클래스는 plus 연산자만 정의하고, 변경 가능한 클래스는 plusAssign 연산자만 정의하자.
     */

    /**
     * 컬렉션에 대해서는 plus, plusAssign 두가지 접근방법을 함께제공한다.
     * +와 -는 새로운 컬렉션을 반환하고, +=와 -=는 기존 컬렉션을 변경한다.
     */
    val list = arrayListOf(1, 2)
    list += 3 // list를 변경, 변경가능한 컬렉션만 사용가능.
    println(list) // [1, 2, 3]

    val newList = list + listOf(4, 5) // list를 변경하지 않고 새로운 리스트를 반환
    println(newList) // [1, 2, 3, 4, 5]
}