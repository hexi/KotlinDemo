package com.hexi.kotlindemo.test.arithmetic

import com.hexi.kotlindemo.test.arithmetic.data.ListNode

object TwoNumbersAdd {
    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val result = ListNode(0)
        var current = result
        var p1 = l1
        var p2 = l2
        var carry = 0

        while (p1 != null || p2 != null) {
            val x: Int = p1?.`val` ?: 0
            val y: Int = p2?.`val` ?: 0
            val sum = carry + x + y
            carry = sum / 10
            current.next = ListNode(sum % 10)
            current.next?.let { current = it }
            p1 = p1?.next
            p2 = p2?.next
        }

        if (carry > 0) {
            current.next = ListNode(carry)
        }
        return result.next
    }
}