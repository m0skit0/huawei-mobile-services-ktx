package org.m0skit0.android.huawei.core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend fun <T> withMainContext(block: suspend CoroutineScope.() -> T): T =
    withContext(Dispatchers.Main, block)
