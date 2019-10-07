package com.hexi.kotlindemo.arithmetic

import com.google.gson.Gson
import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import com.hexi.kotlindemo.test.arithmetic.data.ListNode
import org.junit.Test

class TwoNumbersAddTest {
    @Test
    fun testAddTwoNumbers() {
        val node1 = ListNode(2).apply {
            next = ListNode(4).apply {
                next = ListNode(3)
            }
        }
        val node2 = ListNode(5).apply {
            next = ListNode(6).apply {
                next = ListNode(4)
            }
        }
        println(Gson().toJson(Arithmetic.addTwoNumbers(node1, node2)))
    }
}