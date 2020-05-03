package org.m0skit0.android.huawei.push

import arrow.core.Either
import com.huawei.hms.push.HmsMessaging
import org.m0skit0.android.huawei.core.hms.huaweiDeregisterHMSToken
import org.m0skit0.android.huawei.core.hms.huaweiDeregisterHMSTokenMaybe
import org.m0skit0.android.huawei.core.hms.huaweiHMSToken
import org.m0skit0.android.huawei.core.hms.huaweiHMSTokenMaybe
import org.m0skit0.android.huawei.core.utils.koin
import org.m0skit0.android.huawei.core.utils.suspendUntilCompletion
import org.m0skit0.android.huawei.core.utils.toUnit

private const val TYPE = "HCM"

suspend fun huaweiPushToken(): String = huaweiHMSToken(TYPE)
suspend fun huaweiPushTokenMaybe(): Either<Throwable, String> = huaweiHMSTokenMaybe(TYPE)

suspend fun huaweiDeregisterPushToken(token: String): String = huaweiDeregisterHMSToken(token, TYPE)

suspend fun huaweiDeregisterPushTokenMaybe(token: String): Either<Throwable, String> =
    huaweiDeregisterHMSTokenMaybe(token, TYPE)

suspend fun huaweiTurnOnPush(): Unit =
    koin().get<HmsMessaging>().turnOnPush().suspendUntilCompletion().toUnit()

suspend fun huaweiTurnOnPushMaybe(): Either<Throwable, Unit> = Either.catch { huaweiTurnOnPush() }

suspend fun huaweiTurnOffPush(): Unit =
    koin().get<HmsMessaging>().turnOffPush().suspendUntilCompletion().toUnit()

suspend fun huaweiTurnOffPushMaybe(): Either<Throwable, Unit> = Either.catch { huaweiTurnOffPush() }
