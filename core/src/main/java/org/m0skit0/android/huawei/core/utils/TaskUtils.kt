package org.m0skit0.android.huawei.core.utils

import arrow.core.Either
import com.huawei.hmf.tasks.Task
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend inline fun <T> Task<T>.suspendUntilCompletion(): T =
    suspendCoroutine { continuation ->
        addOnCompleteListener {
            continuation.resume(it.result)
        }
        .addOnFailureListener {
            continuation.resumeWithException(it)
        }
    }

suspend inline fun <T> Task<T>.suspendUntilCompletionMaybe(): Either<Throwable, T> =
    Either.catch {
        suspendCoroutine { continuation ->
            addOnCompleteListener {
                continuation.resume(it.result)
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }
        }
    }
