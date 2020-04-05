package org.m0skit0.android.huawei.core.agc

import arrow.core.Either
import org.m0skit0.android.huawei.core.di.AGCModuleProvider.NAMED_HUAWEI_APP_ID
import org.m0skit0.android.huawei.core.utils.koin

suspend fun huaweiAppId(): String = koin().get(NAMED_HUAWEI_APP_ID)
suspend fun huaweiAppIdMaybe(): Either<Throwable, String> = Either.catch { huaweiAppId() }
