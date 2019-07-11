package com.hexi.kotlindemo.test.genericsKotlin

import com.hexi.kotlindemo.test.genericsKotlin.data.Dog
import com.hexi.kotlindemo.test.genericsKotlin.data.Suckler

/**
 * Created by hexi on 2017/8/23.
 */

/**
 * 测试星投影
 */

class Bar<in T, out U>() {
    fun add(t: T) {

    }

    fun get(): U? {
        return null
    }
}

fun test0(bar: Bar<*, Suckler>) {
    //添加不了，因为我不知道你的类型是什么
//    bar.add(Animal())
}

fun test1(bar: Bar<Suckler, *>) {
    bar.add(Suckler())
    bar.add(Dog())

    bar.get()
}

fun test2(bar: Bar<*, *>) {
    //添加不了，因为我不知道你的类型是什么
//    bar.add(Animal())

    bar.get()
}