package com.hexi.kotlindemo.arithmetic.binaryTree

import org.junit.Test

class TestArrayToBST {
    @Test
    fun test0() {
        val nums = intArrayOf(-10, -5, -3, 0, 5, 9)
        val node = ArrayToBST.sortedArrayToBST(nums)
        TreeNodePrinter.print(node)
    }
}