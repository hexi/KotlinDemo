package com.hexi.kotlindemo.test.extendProperty

/**
 * Created by hexi on 2017/8/19.
 */
class B(override var y: Int) : A() {
    override val x: Int = 2

    override var animal: Dog = Dog()
        set(value) {
            field = value
        }
}