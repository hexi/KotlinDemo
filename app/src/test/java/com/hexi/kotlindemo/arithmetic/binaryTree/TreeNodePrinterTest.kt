package com.hexi.kotlindemo.arithmetic.binaryTree

import org.junit.Test

class TreeNodePrinterTest {

    @Test
    fun test1() {
        val q = TreeNodeUtil.arrayToTreeNode(arrayOf(3, 1, 4, 3, null, 1, 5))
        TreeNodePrinter.print(q)
    }

    @Test
    fun test2() {
        val q = TreeNodeUtil.arrayToTreeNode(arrayOf(3, 1, 4, 3, 6, 1, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, null, 26, 27, null, 28))
        TreeNodePrinter.print(q)
    }
}