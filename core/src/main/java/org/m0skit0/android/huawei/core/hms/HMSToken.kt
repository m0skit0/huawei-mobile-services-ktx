package org.m0skit0.android.huawei.core.hms

import arrow.core.Either
import arrow.core.Option
import com.huawei.hms.aaid.HmsInstanceId
import org.m0skit0.android.huawei.core.di.AGCModuleProvider.NAMED_HUAWEI_APP_ID
import org.m0skit0.android.huawei.core.utils.koin
import org.m0skit0.android.huawei.core.utils.withMainContext

/**
 * For now this only supports EMUI 10.0 or higher.
 * EMUI < 10.0 uses callback for the token.
 * See https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/push-basic-capability
 */
suspend fun huaweiHMSToken(type: String): String =
    withMainContext {
        koin().run {
            get<HmsInstanceId>().getToken(get(NAMED_HUAWEI_APP_ID), type)
        }
    }

/**
 * For now this only supports EMUI 10.0 or higher.
 * EMUI < 10.0 uses callback for the token.
 * See https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/push-basic-capability
 */
suspend fun huaweiHMSTokenMaybe(type: String): Either<Throwable, String> =
    Either.catch { huaweiHMSToken(type) }

suspend fun huaweiDeregisterHMSToken(token: String, type: String) {
    withMainContext {
        koin().get<HmsInstanceId>().deleteToken(token, type)
    }
}

suspend fun huaweiDeregisterHMSTokenMaybe(token: String, type: String): Option<Throwable> =
    Either.catch { huaweiDeregisterHMSToken(token, type) }.swap().toOption()
