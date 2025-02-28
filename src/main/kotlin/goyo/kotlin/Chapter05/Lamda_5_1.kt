package goyo.kotlin.Chapter05

/**
 * 람다란 이름없는 함수
 *
 * 5.1.3 람다식의 문법
 *   - 코틀린 람다식은 항상 중괄호{} 로 둘러싸여 있다.
 *   - 인자 목록 주변에 괄호가 없다.
 *   - 화살표 -> 가 인자 목록과 람다 본문을 구분한다.
 *   - 람다식을 변수에 저장 할 수 있다.
 */
data class Person(val name: String, val age: Int)

fun main() {
    val sum = {x: Int, y: Int -> x + y}
    println(sum(1, 2))
    run {println(42)}

    val people = listOf(Person("Alice", 29), Person("Bob", 31))

    // maxBy() 함수 안에 람다식을 넣어서 전달한다.
    println(people.maxBy({p: Person -> p.age}))

    // 코틀린에서는 함수 호출 시 맨 뒤에 있는 인자가 람다식이면 그 람다를 괄호 밖으로 뺄수있는 문법이 있다.
    println(people.maxBy() {p: Person -> p.age}) // 인자를 밖으로 뺌

    // 람다가 함수의 유일한 인자면 괄호도 없어도 된다.
    println(people.maxBy {p: Person -> p.age})

    // 컴파일러가 문맥으로부터 인자 타입을 유추할 수 있으면 적을 필요없다.
    println(people.maxBy {p -> p.age}) // 파라미터 타입을 생략(컴파일러가 추론), TIP: 처음에는 타입빼고 컴파일러가 에러 발생할때만 기입한다.

    // 람다의 파라미터 디폴트 이름인 it으로 변경
    println(people.maxBy {it.age}) // 람다의 파라미터가 한개뿐이여야 한다.


    /**
     * NOTE : 람다안에 람다가 중첩되는 경우 it 남용을 주의하자
     */


}

