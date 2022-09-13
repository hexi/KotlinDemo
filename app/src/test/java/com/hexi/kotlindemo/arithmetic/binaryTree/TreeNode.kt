package com.hexi.kotlindemo.arithmetic.binaryTree

class TreeNode<T>(
    val value: T,
    var left: TreeNode<T>? = null,
    var right: TreeNode<T>? = null,
) {
    fun getText(): String {
        return this.value.toString()
    }
}