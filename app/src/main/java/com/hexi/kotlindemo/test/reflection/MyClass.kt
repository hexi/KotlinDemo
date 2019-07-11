package com.hexi.kotlindemo.test.reflection

/**
 * Created by hexi on 2017/9/20.
 */
class MyClass(name: String, age: Int) {
    val name = name
    val age = age

    constructor(): this("fromNoArgument", 0)

    constructor(name: String): this(name, 0)

    override fun toString(): String {
        return "{\"name\": \"${this.name}\", \"age\": ${this.age}}"
    }
}