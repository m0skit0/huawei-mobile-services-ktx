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
import org.m0skit0.android.huawei.core.utils.suspendUntilCompletion
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun huaweiSignInIntent(): Intent =
    with(koin()) {
        get<HuaweiIdAuthParamsHelper>()
            .setIdToken()
            .createParams()
            .let { params ->
                get<(HuaweiIdAuthParams) -> HuaweiIdAuthService>(NAMED_AUTH_SERVICE)(params).signInIntent
            }
    }

suspend fun huaweiSignInIntentMaybe(): Either<Throwable, Intent> =
    Either.catch { huaweiSignInIntent() }

fun huaweiSignInIntentWithCode(): Intent =
    with(koin()) {
        get<HuaweiIdAuthParamsHelper>()
            .setAuthorizationCode()
            .createParams()
            .let { params ->
                get<(HuaweiIdAuthParams) -> HuaweiIdAuthService>(NAMED_AUTH_SERVICE)(params).signInIntent
            }
    }

suspend fun huaweiSignInIntentWithCodeMaybe(): Either<Throwable, Intent> =
    Either.catch { huaweiSignInIntentWithCode() }

suspend fun Intent.huaweiParseAuthenticationResult(): AuthHuaweiId =
    HuaweiIdAuthManager.parseAuthResultFromIntent(this).suspendUntilCompletion()

suspend fun Intent.huaweiParseAuthenticationResultMaybe(): Either<Throwable, AuthHuaweiId> =
    Either.catch { huaweiParseAuthenticationResult() }

suspend fun Intent.huaweiParseAndVerifyAuthenticationResult(): AuthHuaweiId =
    huaweiParseAuthenticationResult().verify().suspended()

suspend fun Intent.huaweiParseAndVerifyAuthenticationResultMaybe(): Either<Throwable, AuthHuaweiId> =
    huaweiParseAuthenticationResult().verify().attempt().suspended()

fun huaweiSignOut() {
    with(koin()) {
        get<HuaweiIdAuthParamsHelper>()
            .createParams()
            .let { params ->
                get<(HuaweiIdAuthParams) -> HuaweiIdAuthService>(NAMED_AUTH_SERVICE)(params).signOut()
            }
    }
}

suspend fun huaweiSignOutMaybe(): Either<Throwable, Unit> = Either.catch { huaweiSignOut() }

suspend fun huaweiSilentSignIn(): AuthHuaweiId =
    with(koin()) {
        get<HuaweiIdAuthParamsHelper>()
            .createParams()
            .let { params ->
                get<(HuaweiIdAuthParams) -> HuaweiIdAuthService>(NAMED_AUTH_SERVICE)
                    .invoke(params)
                    .silentSignIn().let { task ->
                        suspendCoroutine { continuation ->
                            task.addOnSuccessListener {
                                continuation.resume(it)
                            }.addOnFailureListener {
                                continuation.resumeWithException(it)
                            }
                        }
                    }
            }
    }

suspend fun huaweiSilentSignInMaybe(): Either<Throwable, AuthHuaweiId> = Either.catch { huaweiSilentSignIn() }
