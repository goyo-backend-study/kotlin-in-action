package goyo.kotlin.Chapter05

fun isEven(n: Int) = n % 2 == 0

/**
 * 컬렉션 함수형 API
 */
class Book(val title: String, val author: List<String>)

fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 26), Person("Bob", 31), Person("Carol", 32))

    // filter 함수는 람다의 각 원소를 넘겨서 true를 반환하는 원소만 모음
    // 원소를 변환할수는 없음
    val list = listOf(1, 2, 3, 4, 5)
    println(list.filter { it % 2 == 0 })
    println(list.filter(::isEven))

    // 각 원소 변환해서 새 컬렉션으로 만드는건 map 함수
    // 새로운 컬렉션으로 변환된다.
    println(list.map{it * it})
    println(people.map(Person::name))

    // 100번의 maxBy() 실행
    people.filter {it.age == people.maxBy(Person::age).age}

    // 1번의 maxBy() 실행
    val maxAge = people.maxBy(Person::age).age
    people.filter {it.age == maxAge}


    val canBeInClub27 = { p: Person -> p.age <= 26 }
    println(people.all(canBeInClub27)) // 모든 원소가 술어를 만족하는지
    println(people.any(canBeInClub27)) // 술어를 만족하는 원소가 하나라도 있는지
    
    // 술어를 만족하는 하나 찾기, 만족하는 원소 찾으면 바로 반환 없는 경우 null 반환
    println(people.find(canBeInClub27))
    println(people.firstOrNull(canBeInClub27)) // find와 사실상 같음

    // 컬렉션 그룹으로 나눔
    // Map<Int, List<Person>> 으로 변환
    println(people.groupBy(Person::age))

    val books = listOf(
        Book("Thursday Next", listOf("James", "Johnson")),
        Book("Mort", listOf("Terry Pratchettles")),
        Book("Good Omens", listOf("Terry Pratchettles", "Neil Gaiman")),
    )

    // 작가를 모아둔 컬렉션
    println(books.flatMap { it.author }.toSet()) // toSet() 으로 중복제거

    // 리스트 안에 리스트 요소를 꺼내서 하나의 리스트로 만든다.
    val nestedList = listOf(
        listOf("Apple", "Banana"),
        listOf("Orange"),
        listOf("Grape", "Peach")
    )
    println(nestedList.flatten())
}