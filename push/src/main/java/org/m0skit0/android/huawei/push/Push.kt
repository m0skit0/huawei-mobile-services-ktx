package org.m0skit0.android.huawei.push

import arrow.core.Either
import arrow.core.Option
import com.huawei.hms.push.HmsMessaging
import org.m0skit0.android.huawei.core.hms.huaweiDeregisterHMSToken
import org.m0skit0.android.huawei.core.hms.huaweiDeregisterHMSTokenMaybe
import org.m0skit0.android.huawei.core.hms.huaweiHMSToken
import org.m0skit0.android.huawei.core.hms.huaweiHMSTokenMaybe
import org.m0skit0.android.huawei.core.utils.koin
import org.m0skit0.android.huawei.core.utils.suspendCoroutineUntilCompletion
import org.m0skit0.android.huawei.core.utils.toUnit

private const val TYPE = "HCM"

suspend fun huaweiPushToken(): String = huaweiHMSToken(TYPE)
suspend fun huaweiPushTokenMaybe(): Either<Throwable, String> = huaweiHMSTokenMaybe(TYPE)

suspend fun huaweiDeregisterPushToken(token: String): Unit = huaweiDeregisterHMSToken(token, TYPE)
suspend fun huaweiDeregisterPushTokenMaybe(token: String): Option<Throwable> = huaweiDeregisterHMSTokenMaybe(token, TYPE)

suspend fun huaweiTurnOnPush(): Unit = koin().get<HmsMessaging>().turnOnPush().suspendCoroutineUntilCompletion().toUnit()
suspend fun huaweriTurnOnPushMaybe(): Option<Throwable> = Either.catch { huaweiTurnOnPush() }.swap().toOption()

suspend fun huaweiTurnOffPush(): Unit = koin().get<HmsMessaging>().turnOffPush().suspendCoroutineUntilCompletion().toUnit()
suspend fun huaweiTurnOffPushMaybe(): Option<Throwable> = Either.catch { huaweiTurnOffPush() }.swap().toOption()

