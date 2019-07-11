package com.hexi.kotlindemo.test

/**
 * Created by hexi on 2017/7/22.
 */
class Person(name: String, age: Int = 0) : Animal(name) {
    var age: Int = if (age > 0) age else 0

    init {
        println("init person, age:${this.age}")
    }

    private var children: MutableList<Person> = mutableListOf()

    var isEmpty: Boolean = children.isEmpty()
        get() {
            println("get isEmpty")
            return this.children.size == 0
        }
        set(value) {
            println("set isEmpty")
            field = value
        }

    constructor(name: String, person: Person) : this(name) {
        children.add(person)
    }

    override fun run() {
        println("running from person")
    }
}

public fun main(args: Array<String>) {
    val p = Person("hexi", 100)
    println(p.isEmpty)
    p.isEmpty = false
    println(p.isEmpty)
}
