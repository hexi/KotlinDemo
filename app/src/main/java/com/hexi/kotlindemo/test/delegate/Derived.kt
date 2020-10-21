package com.hexi.kotlindemo.test.delegate

/**
 * Created by hexi on 2017/7/23.
 */
class Derived(val b: Base = BaseImpl(20)): Base by b {
    override fun print() {
        b.print()
        print("print of derived")
    }
}

fun main() {
    Derived().print()
}