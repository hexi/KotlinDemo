package com.hexi.kotlindemo.test.delegate

/**
 * Created by hexi on 2017/7/23.
 */
class BaseImpl(val x: Int): Base {
    override fun print() {
        println(x)
    }
}