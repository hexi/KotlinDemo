package com.hexi.kotlindemo.test.delegateProperty

import kotlin.reflect.KProperty

/**
 * Created by hexi on 2017/7/23.
 */
class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println( "$thisRef, thank you for delegating '${property.name}' to me!")

        return "from delegate"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}

fun main(args: Array<String>) {
    val ex = Example()
    println(ex.p)
    println(ex.p)
    ex.p = "A"
    println(ex.p)
    ex.p = "B"
    println(ex.p)
}
