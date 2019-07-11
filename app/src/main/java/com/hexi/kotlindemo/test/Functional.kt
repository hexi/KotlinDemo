package com.hexi.kotlindemo.test

import com.hexi.kotlindemo.test.data.TreeNode

/**
 * Created by hexi on 2017/7/25.
 */

//高阶函数是将函数用作参数或返回值的函数
fun <T, R> List<T>.myMap(transform: (T) -> R): List<R> {
    println("map....")
    val result = arrayListOf<R>()
    for (item in this) {
        result.add(transform(item))
    }

    return result
}

fun ordinaryFunction(call: () -> String): String {
    return call()
}

fun foo(): String {
    val result0 = ordinaryFunction {
        return@ordinaryFunction "returned from ordinaryFunction"
    }
    println(result0)
    return "returned from foo"
}

inline fun inlineFunction(call: () -> String): String {
    return call()
}

fun bar(): String {
    val s = inlineFunction {
        "returned from inlineFunction"
    }
    println(s)
    return "returned from bar"
}

inline fun crossinlineF(crossinline body: () -> Unit) {
    val f = object : Runnable {
        override fun run() = body()
    }
}

fun <T> TreeNode.findParentOfType0(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }

    return p as T?
}

//具体化的类型参数
//我们使用 reified 修饰符来限定类型参数，现在可以在函数内部访问它了，
// 几乎就像是一个 普通的类一样。
inline fun <reified T> TreeNode.findParentOfType1(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }

    return p as T?
}

inline fun <reified T> membersOf() = T::class.members

suspend fun doSomething() {
    println("doSomething...")
    Thread.sleep(60 * 1000)
    println("after sleep...")
}

fun main(args: Array<String>) {
    val list = arrayListOf<String>("1", "2", "3")
    val ints: List<Int> = list.myMap { item -> item.toInt() }
    println(ints)
    println(foo())
    println(bar())


    println(membersOf<StringBuilder>().joinToString("\n"))

//    async {
//        doSomething()
//    }
//    println("after doSomething")
}