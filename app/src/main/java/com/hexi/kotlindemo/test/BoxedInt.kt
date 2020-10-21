package com.hexi.kotlindemo.test

/**
 * Created by hexi on 2017/7/17.
 */
class BoxedInt {
    fun convert() {
        val a: Int? = 1
        //自动装箱不能隐式转换
//        val b: Long? = a
//        print(a == b)
    }
}

fun main() {
    val a: Int = 10000
    println(a === a)
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println("boxedA === anotherBoxedA? ${boxedA === anotherBoxedA}")
    println("boxedA == anotherBoxedA? ${boxedA == anotherBoxedA}")
}