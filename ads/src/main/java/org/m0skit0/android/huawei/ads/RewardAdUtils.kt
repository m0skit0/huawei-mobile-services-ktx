package org.m0skit0.android.huawei.ads

import android.app.Activity
import arrow.core.Either
import arrow.core.Option
import arrow.core.toOption
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.reward.Reward
import com.huawei.hms.ads.reward.RewardAd
import com.huawei.hms.ads.reward.RewardAdLoadListener
import com.huawei.hms.ads.reward.RewardAdStatusListener
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class RewardAdException(messsage: String) : Exception(messsage)

suspend fun RewardAd.load(param: AdParam = AdParam.Builder().build()): RewardAd = apply {
    suspendCoroutine<RewardAd> { continuation ->
        loadAd(param, object : RewardAdLoadListener() {
            override fun onRewardedLoaded() {
                super.onRewardedLoaded()
                continuation.resume(this@load)
            }
            override fun onRewardAdFailedToLoad(errorCode: Int) {
                super.onRewardAdFailedToLoad(errorCode)
                continuation.resumeWithException(IOException("Reward ad failed to load with error code $errorCode"))
            }
        })
    }
}

suspend fun RewardAd.loadMaybe(param: AdParam = AdParam.Builder().build()): Either<Throwable, RewardAd> =
    Either.catch { load(param) }

suspend fun RewardAd.showAndWaitForReward(activity: Activity): Option<Reward> =
    suspendCoroutine { continuation ->
        show(activity, object : RewardAdStatusListener() {
            override fun onRewardAdClosed() {
                super.onRewardAdClosed()
                continuation.resumeWithException(RewardAdException("Reward ad was closed"))
            }
            override fun onRewardAdFailedToShow(errorCode: Int) {
                super.onRewardAdFailedToShow(errorCode)
                continuation.resumeWithException(RewardAdException("Reward ad failed to load with error code $errorCode"))
            }
            override fun onRewarded(reward: Reward?) {
                super.onRewarded(reward)
                continuation.resume(reward.toOption())
            }
        })
    }

suspend fun RewardAd.showAndWaitForRewardMaybe(activity: Activity): Either<Throwable, Option<Reward>> =
    Either.catch { showAndWaitForReward(activity) }
