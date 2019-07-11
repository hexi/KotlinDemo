package com.hexi.kotlindemo.coroutineSupport

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

class LifecycleCoroutineScope : CoroutineScope, Closeable {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun close() {
        job.cancel()
    }
}