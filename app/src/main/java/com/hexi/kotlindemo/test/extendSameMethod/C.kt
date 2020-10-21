package com.hexi.kotlindemo.test.extendSameMethod

/**
 * Created by hexi on 2017/7/22.
 */
class C: A(), B {
    override fun f() {
        super<A>.f()
        super<B>.f()
    }
}

fun main() {
    val c  = C()
    c.f()
    c.a()
    c.b()
}