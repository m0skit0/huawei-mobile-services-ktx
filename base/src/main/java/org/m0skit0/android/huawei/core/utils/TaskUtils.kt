package org.m0skit0.android.huawei.core.utils

import com.huawei.hmf.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend inline fun <T> Task<T>.suspendUntilCompletion(): T =
    suspendCancellableCoroutine { continuation ->
        addOnCompleteListener {
            continuation.resume(it.result)
        }
        .addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }
