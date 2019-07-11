package com.hexi.kotlindemo.test.extendProperty

/**
 * Created by hexi on 2017/8/19.
 */
open class A {
    open val x: Int get() = 1

    open var y: Int = 0

    open val animal: Animal get() = Animal()
}