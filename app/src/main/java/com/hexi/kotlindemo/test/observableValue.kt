package com.hexi.kotlindemo.test

import kotlin.properties.Delegates

/**
 * Created by hexi on 2017/7/23.
 */

class User {
    var name: String by Delegates.observable("initialName") {
        property, oldValue, newValue ->
        println("${property.name}, $oldValue -> $newValue")
    }

    var age: Int by Delegates.vetoable(0) {
        property, oldValue, newValue ->
        println("${property.name}, $oldValue -> $newValue")

        newValue > 0
    }
}

fun main() {
    val user = User()
    user.name = "first"
    user.name = "second"

    user.age = -100
    println(user.age)
    user.age = 10
    println(user.age)
    user.age = -10
    println(user.age)
}