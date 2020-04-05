package org.m0skit0.android.huawei.push

import arrow.core.Either
import arrow.core.Option
import org.m0skit0.android.huawei.core.hms.huaweiDeregisterHMSToken
import org.m0skit0.android.huawei.core.hms.huaweiDeregisterHMSTokenMaybe
import org.m0skit0.android.huawei.core.hms.huaweiHMSToken
import org.m0skit0.android.huawei.core.hms.huaweiHMSTokenMaybe

private const val TYPE = "HCM"

suspend fun huaweiPushToken(): String = huaweiHMSToken(TYPE)
suspend fun huaweiPushTokenMaybe(): Either<Throwable, String> = huaweiHMSTokenMaybe(TYPE)

suspend fun huaweiDeregisterPushToken(token: String): Unit = huaweiDeregisterHMSToken(token, TYPE)
suspend fun huaweiDeregisterPushTokenMaybe(token: String): Option<Throwable> = huaweiDeregisterHMSTokenMaybe(token, TYPE)
