package org.m0skit0.android.huawei.core.utils

import com.huawei.hmf.tasks.Task
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend inline fun <T> Task<T>.suspendCoroutineUntilCompletion(): T =
    suspendCoroutine { continuation ->
        addOnCompleteListener {
            continuation.resume(it.result)
        }
        .addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }
