package com.hexi.kotlindemo.test

/**
 * Created by hexi on 2017/7/22.
 */
open class Animal constructor(var name: String) {
    open fun run() {
        println("running from animal")
    }
}