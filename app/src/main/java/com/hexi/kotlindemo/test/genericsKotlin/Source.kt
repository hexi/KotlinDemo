package com.hexi.kotlindemo.test.genericsKotlin

import com.hexi.kotlindemo.test.genericsKotlin.data.Dog
import com.hexi.kotlindemo.test.genericsKotlin.data.Suckler

/**
 * Created by hexi on 2017/7/11.
 */
class Source<out T> {
    fun get():T? {
        return null
    }

    //out 声明的类型参数不能作为参数传入，否则编译不通过
//    fun add(t: T) {
//
//    }

    //只能将Suckler或其子类赋值给t
    //好比我有一个只能往外取Suckler的箱子，你把这个箱子换成装Dog的箱子也是没问题的，因为你取出来的Dog可以当Suckler使用
    fun match(source: Source<Dog>) {
        var t: Source<Suckler> = source
//        val dog: Dog = t.get()
    }

    //out声明的泛型只能将String或者子类赋值给t，下面编译不通过
//    fun mismatch(tree: Source<Animal>) {
//        var t: Source<Dog> = tree
//    }
}
