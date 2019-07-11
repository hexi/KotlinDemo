package com.hexi.kotlindemo.test

/**
 * Created by hexi on 2017/7/23.
 */

class MapUser(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

fun main(args: Array<String>) {
    val user = MapUser(mapOf(
            "name" to "John Doe",
            "age" to 100
    ));
    println(user.name)
    println(user.age)
}