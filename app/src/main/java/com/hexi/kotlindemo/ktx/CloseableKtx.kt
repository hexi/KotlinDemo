package com.hexi.kotlindemo.ktx

import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.Closeable

suspend inline fun <T : Closeable?, R> T.useCancellably(crossinline block: (T) -> R): R = suspendCancellableCoroutine { cont ->
    cont.resume(use(block)) {
        this?.close()
    }
}