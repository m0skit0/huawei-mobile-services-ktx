package org.m0skit0.android.huawei.core.utils

import com.huawei.hmf.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun <T> Task<T>.suspendForCompletion(): Task<T> =
    suspendCancellableCoroutine {
        addOnCompleteListener(it::resume)
        .addOnFailureListener(it::resumeWithException)
    }

suspend inline fun <T> Task<T>.suspendForSuccess(crossinline failure: () -> Unit = {}): T =
    suspendCancellableCoroutine { continuation ->
        addOnCompleteListener {
            ifSuccessful(continuation::resume, failure)
        }
        .addOnFailureListener(continuation::resumeWithException)
    }

inline fun <T> Task<T>.ifSuccessful(block: (T) -> Unit, elseBlock: () -> Unit = {}) {
    if (isComplete && isSuccessful) result?.let(block)
    else elseBlock()
}
