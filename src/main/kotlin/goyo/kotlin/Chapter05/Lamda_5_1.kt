package goyo.kotlin.Chapter05

data class Person(val name: String, val age: Int)

fun main() {
    val sum = {x: Int, y: Int -> x + y}
    println(sum(3, 4))
    run { println(sum(3, 4)) }

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxBy({p: Person -> p.age}))
}

