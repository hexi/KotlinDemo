package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.junit.Test

class OneCountTest {
    @Test
    fun testCountOne() {
        val number = 111
        val count = Arithmetic.countOne(number)
        println(count)
    }
}