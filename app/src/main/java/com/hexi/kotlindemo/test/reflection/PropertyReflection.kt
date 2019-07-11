package com.hexi.kotlindemo.test.reflection

import kotlin.reflect.KProperty0
import kotlin.reflect.KProperty1

/**
 * Created by hexi on 2017/9/27.
 */

var x = 1

fun main(args:Array<String>) {
    val xp: KProperty0<Int> = ::x
    println(xp.get())

    val n: KProperty1<MyClass, String> = MyClass::name
    println(n.get(MyClass("MyClass")))

    val m = MyClass()
    val nn: KProperty0<String> = m::name
    println(nn.get())

    val len: KProperty1<String, Int> = String::length
    println("abcde.length: ${len.get("abcde")}")

    val strs = listOf("a", "bc", "def")
    println(strs.map(len)) // 输出 [1, 2, 3]
}