package com.hexi.kotlindemo.test

import org.w3c.dom.Node
import java.util.*

/**
 * Created by hexi on 2017/8/5.
 */

//带接收者的函数字面值
class HTML {
    fun body() {
        println("body()...")
    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML() // 创建接收者对象
    html.init()       // 将该接收者对象传给该 lambda
    return html
}

//操作符重载
data class Point(val x: Int, val y: Int) {
    operator fun unaryMinus(): Point = Point(-x, -y)
}

fun main(args: Array<String>) {
    //类型的检查与转换
    val obj = "aaa"
    if (obj is String) {
        println(obj.length)
    }
    if (obj !is String) {
        println("Not a String")
    } else {
        println(obj.length)
    }

    //智能转换
    fun demo(x: Any) {
        if (x is String) {
            println(x.length)
        }

        if (x !is String) return
        println(x.length)

        //或者在 && 和 || 的右侧:
        // `||` 右侧的 x 自动转换为字符串
        if (x !is String || x.length == 0) return
        // `&&` 右侧的 x 自动转换为字符串
        if (x is String && x.length > 0) {
            println(x.length)
        }

        //这些 智能转换 用于 when -表达式 和 while -循环 也一样:
        when (x) {
            is Int -> println(x + 1)
            is String -> println(x.length + 1)
            is IntArray -> println(x.sum())
        }
    }


    val y: String? = null
    //“不安全的”转换操作符
    val z: String? = y as String?
    //“安全的”(可空)转换操作符
    val x: String? = y as? String
    println(x)

    html {
        // 带接收者的 lambda 由此开始
        body()  // 调用该接收者对象的一个方法
    }

    val point = Point(10, 20)
    println(-point)

    //安全的调用
    val b: String? = null
    b?.length

    //如果要只对非空值执行某个操作，安全调用操作符可以与 let 一起使用
    val listWithNulls: List<String?> = listOf("A", null)
    for (item in listWithNulls) {
        item?.let { println(it) }
    }

    var l: Int = if (b != null) b.length else -1
    //还可以用下面的Elvis 操作符表达实现相同的效果
    l = b?.length ?: -1

    //请注意，因为 throw 和 return 在 Kotlin 中都是表达式，
    // 所以它们也可以用在 elvis 操作符 右侧。这可能会非常方便，例如，检查函数参数:
    fun foo(node: Node): String? {
        val parent = node.parentNode ?: return null
        return node.nodeName ?: throw IllegalArgumentException("name expected")
    }

    //安全的类型转换
    val aInt: Int? = b as? Int

    //类引用
    val c = OtherA::class
    val ac = obj::class

    //函数引用
    fun isOdd(x: Int) = x % 2 != 0

    val numbers = listOf(1, 2, 3)
    println(numbers.filter(::isOdd))


}

//在 Kotlin 中调用 Java 代码
fun demo(source: List<Int>) {
    var list = java.util.ArrayList<Int>()
    //“for”-循环用于 Java 集合:
    for (item in source) {
        list.add(item)
    }
    // 操作符约定同样有效:
    for (i in 0..source.size - 1) {
        list[i] = source[i] // 调用 get 和 set
    }

}

fun calendarDemo() {
    val calendar = java.util.Calendar.getInstance()
    if (calendar.firstDayOfWeek == Calendar.SUNDAY) {   // 调用 getFirstDayOfWeek()
        calendar.firstDayOfWeek = Calendar.MONDAY       // 调用 setFirstDayOfWeek()
    }
}

class OtherA { // 隐式标签 @OtherA
    inner class B {  // 隐式标签 @B
        fun Int.foo() { // 隐式标签 @foo
            val a = this@OtherA //OtherA 的  this
            val b = this@B //B 的 this

            val c = this // foo()的接收者，一个Int
            val c1 = this@foo // foo()的接收者，一个Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit 的接收者，一个String
            }


            val funLit2 = { s: String ->
                // foo() 的接收者，因为它包含的 lambda 表达式
                // 没有任何接收者
                val d1 = this
            }
        }
    }
}
