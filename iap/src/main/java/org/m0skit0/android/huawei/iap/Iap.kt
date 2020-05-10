package org.m0skit0.android.huawei.iap

import android.content.Intent
import arrow.core.Either
import com.huawei.hmf.tasks.Task
import com.huawei.hms.iap.IapClient
import com.huawei.hms.iap.entity.*
import org.m0skit0.android.huawei.core.utils.koin
import org.m0skit0.android.huawei.core.utils.suspendUntilCompletion
import org.m0skit0.android.huawei.core.utils.suspendUntilCompletionMaybe
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun huaweiIsIAPEnvironmentReady(): IsEnvReadyResult =
    isEnvReady().suspendUntilCompletion()

suspend fun huaweiIsIAPEnvironmentReadyMaybe(): Either<Throwable, IsEnvReadyResult> =
    isEnvReady().suspendUntilCompletionMaybe()

private fun isEnvReady(): Task<IsEnvReadyResult> = koin().get<IapClient>().isEnvReady

suspend fun ProductInfoReq.obtain(): ProductInfoResult =
    obtainProductInfo().suspendUntilCompletion()

suspend fun ProductInfoReq.obtainMaybe(): Either<Throwable, ProductInfoResult> =
    obtainProductInfo().suspendUntilCompletionMaybe()

private fun ProductInfoReq.obtainProductInfo(): Task<ProductInfoResult> =
    koin().get<IapClient>().obtainProductInfo(this)

suspend fun PurchaseIntentReq.purchase(): PurchaseIntentResult =
    createPurchaseIntent().suspendUntilCompletion()

suspend fun PurchaseIntentReq.purchaseMaybe(): Either<Throwable, PurchaseIntentResult> =
    createPurchaseIntent().suspendUntilCompletionMaybe()

suspend fun PurchaseIntentResult.purchase(): PurchaseResultInfo =
    suspendCoroutine { continuation ->
        if (status.hasResolution()) {
            HuaweiIapBridgeActivity.start(status, {
                continuation.resumeWithException(this)
            }) {
                continuation.resume(this)
            }
        } else {
            continuation.resumeWithException(HuaweiIapFailed(status.statusCode))
        }
    }

private fun PurchaseIntentReq.createPurchaseIntent(): Task<PurchaseIntentResult> =
    koin().get<IapClient>().createPurchaseIntent(this)

fun Intent.parsePurchaseResult(): PurchaseResultInfo =
    koin().get<IapClient>().parsePurchaseResultInfoFromIntent(this)

suspend fun ConsumeOwnedPurchaseReq.consume(): ConsumeOwnedPurchaseResult =
    consumeOwnedPurchase().suspendUntilCompletion()

suspend fun ConsumeOwnedPurchaseReq.consumeMaybe(): Either<Throwable, ConsumeOwnedPurchaseResult> =
    consumeOwnedPurchase().suspendUntilCompletionMaybe()

private fun ConsumeOwnedPurchaseReq.consumeOwnedPurchase(): Task<ConsumeOwnedPurchaseResult> =
    koin().get<IapClient>().consumeOwnedPurchase(this)

suspend fun OwnedPurchasesReq.obtain(): OwnedPurchasesResult =
    obtainOwnedPurchases().suspendUntilCompletion()

suspend fun OwnedPurchasesReq.obtainMaybe(): Either<Throwable, OwnedPurchasesResult> =
    obtainOwnedPurchases().suspendUntilCompletionMaybe()

private fun OwnedPurchasesReq.obtainOwnedPurchases(): Task<OwnedPurchasesResult> =
    koin().get<IapClient>().obtainOwnedPurchases(this)

suspend fun OwnedPurchasesReq.obtainRecord(): OwnedPurchasesResult =
    obtainOwnedPurchasesRecord().suspendUntilCompletion()

suspend fun OwnedPurchasesReq.obtainRecordMaybe(): Either<Throwable, OwnedPurchasesResult>  =
    obtainOwnedPurchasesRecord().suspendUntilCompletionMaybe()

private fun OwnedPurchasesReq.obtainOwnedPurchasesRecord(): Task<OwnedPurchasesResult> =
    koin().get<IapClient>().obtainOwnedPurchaseRecord(this)
