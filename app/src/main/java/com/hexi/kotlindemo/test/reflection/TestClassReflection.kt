package com.hexi.kotlindemo.test.reflection

import java.lang.reflect.Constructor
import kotlin.reflect.KFunction
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.full.starProjectedType

/**
 * Created by hexi on 2017/9/20.
 */

fun main(args: Array<String>) {
    val c = MyClass::class
    val co = c.createInstance()
    println(co.toString())

    val primaryConstructor: KFunction<MyClass>? = c.primaryConstructor
    val newInstance: MyClass? = primaryConstructor?.call("newInstance by primaryConstructor", 10)
    println(newInstance?.toString())

    //使用java反射
    val javaC: Class<MyClass> = c.java
    val constructor: Constructor<MyClass> = javaC.getConstructor(String::class.java)
    val foo: MyClass = constructor.newInstance("newInstance by java")
    println(foo.toString())

    //获取指定的构造函数
    val noArgCon: KFunction<MyClass>? = c.getConstructor()
    val noArgInstance = noArgCon?.call()
    println(noArgInstance?.toString())

    var result: KFunction<MyClass>? = c.getConstructor(String::class.starProjectedType)
    val bar: MyClass? = result?.call("newInstance by parameter")
    println(bar?.toString())

    //绑定的类引用
    val widget: Widget = BadWidget()
    assert(widget is GoodWidget) { "Bad widget: ${widget::class.qualifiedName}" }
}