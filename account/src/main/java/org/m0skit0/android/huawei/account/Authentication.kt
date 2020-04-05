package org.m0skit0.android.huawei.account

import android.content.Intent
import arrow.core.Either
import com.huawei.hms.support.hwid.HuaweiIdAuthManager
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper
import com.huawei.hms.support.hwid.result.AuthHuaweiId
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService
import org.m0skit0.android.huawei.account.di.AuthModuleProvider.NAMED_AUTH_SERVICE
import org.m0skit0.android.huawei.core.utils.koin
import org.m0skit0.android.huawei.core.utils.suspendCoroutineUntilCompletion

fun huaweiSignInIntent(): Intent =
    with(koin()) {
        get<HuaweiIdAuthParamsHelper>()
            .setIdToken()
            .createParams()
            .let { params ->
                get<(HuaweiIdAuthParams) -> HuaweiIdAuthService>(NAMED_AUTH_SERVICE)(params).signInIntent
            }
    }

suspend fun Intent.huaweiParseAuthenticationResult(): AuthHuaweiId =
    HuaweiIdAuthManager.parseAuthResultFromIntent(this).suspendCoroutineUntilCompletion()

suspend fun Intent.huaweiParseAuthenticationResultMaybe(): Either<Throwable, AuthHuaweiId> =
    Either.catch { huaweiParseAuthenticationResult() }
