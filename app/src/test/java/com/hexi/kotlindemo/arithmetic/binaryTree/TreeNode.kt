package com.hexi.kotlindemo.arithmetic.binaryTree

class TreeNode(
    val value: Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
) : TreeNodePrinter.PrintableNode {
    override fun getLeft(): TreeNodePrinter.PrintableNode? {
        return this.left
    }

    override fun getRight(): TreeNodePrinter.PrintableNode? {
        return this.right
    }

    override fun getText(): String {
        return this.value.toString()
    }

}