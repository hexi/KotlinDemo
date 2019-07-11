package com.hexi.kotlindemo.test.reflection

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KType

/**
 * Created by hexi on 2017/9/27.
 */

/**
 * 获取指定参数的构造函数
 */
fun <T: Any> KClass<T>.getConstructor(vararg filterParameters: KType): KFunction<T>? {
    var result: KFunction<T>? = null
    val constructors: Collection<KFunction<T>> = this.constructors
    for (constructor in constructors) {
        val parameters: List<KParameter> = constructor.parameters
        if (filterParameters.isEmpty() && parameters.isEmpty()) {
            return constructor
        }
        if (parameters.size != filterParameters.size) continue

        var found = true
        for ((index, parameter) in parameters.withIndex()) {
            if (filterParameters[index].classifier != parameter.type.classifier) {
                found = false
                break
            }
        }

        if (found) {
            result = constructor
            break
        }
    }

    return result
}