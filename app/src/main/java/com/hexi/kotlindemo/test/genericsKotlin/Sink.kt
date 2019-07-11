package com.hexi.kotlindemo.test.genericsKotlin

import com.hexi.kotlindemo.test.genericsKotlin.data.Animal
import com.hexi.kotlindemo.test.genericsKotlin.data.Dog
import com.hexi.kotlindemo.test.genericsKotlin.data.Suckler

/**
 * Created by hexi on 2017/7/11.
 */
class Sink<in T> {
    fun add(t: T) {

    }

    //in修饰的类型参数不能返回，否则会编译不通过
//    fun get() : T? = null

    //只能将Suckler类型或其父类赋值给 t
    //好比我有一个只能往里面放Suckler的箱子，你把它换成可以装Animal的箱子也是没问题的，因为Suckler和Dog都可以往里面放
    fun match(sink: Sink<Animal>) {
        var t: Sink<Suckler> = sink
        t.add(Suckler())
        t.add(Dog())
//        t.add(Animal)
    }

    //编译不通过，报Type mismatch
//    fun dismiss(router: Sink<Suckler>) {
//        var t: Sink<Animal> = router
//    }
}