package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OneCountTest {
    @Test
    fun testCountOne() {
        val number = 111
        val count = Arithmetic.countOne(number)
        assertThat(count).isEqualTo(6)
    }
}