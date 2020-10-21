package com.hexi.kotlindemo.test

/**
 * Created by hexi on 2017/7/30.
 */

//嵌套类
class NestedOuter {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }
}

//内部类
class InnerOuter {
    private val bar: Int = 1

    inner class Inner {
        fun foo() = bar
    }
}

//匿名内部类
//使用对象表达式创建匿名内部类实例
open class Window {
    class MouseEvent {

    }

    interface MouseAdapter {
        fun mouseClicked(e: MouseEvent)

        fun mouseEntered(e: MouseEvent)
    }

    fun addMouseListener(mouseAdapter: MouseAdapter) {

    }

    object DefaultWindow: Window() {

    }
}

fun testAnonymousInnerClass() {
    val window = Window()
    window.addMouseListener(object : Window.MouseAdapter {
        override fun mouseClicked(e: Window.MouseEvent) {

        }

        override fun mouseEntered(e: Window.MouseEvent) {

        }
    })
}

//初始化枚举
enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

//枚举匿名类
enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },

    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

//可以使用 enumValues<T>() 和 enumValueOf<T>() 函数以泛型的方式访问枚 举类中的常量
inline fun <reified T : Enum<T>> printAllValues() {
    val allNames = enumValues<T>().joinToString {it.name}
    println(allNames)
}

//对象表达式
open class AA(x: Int) {
    public open val y: Int = x
}

interface BB {

}

val ab: AA = object : AA(1), BB {
    override val y = 15
}

fun testObject() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    println(adHoc.x + adHoc.y)
}

fun main() {
    val demo = NestedOuter.Nested().foo()
    println(demo)

    val bar = InnerOuter().Inner().foo()
    println(bar)

    printAllValues<Color>()
}