package org.m0skit0.android.huawei.ads

import com.huawei.hms.ads.AdListener
import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.BannerAdSize
import com.huawei.hms.ads.banner.BannerView

fun BannerView.advertiserId(id: String): BannerView = apply {
    adId = id
}

fun BannerView.size(size: BannerAdSize): BannerView = apply {
    bannerAdSize = size
}

fun BannerView.load(param: AdParam = AdParam.Builder().build()): BannerView = apply {
    loadAd(param)
}

fun BannerView.listener(listener: AdListener): BannerView = apply {
    adListener = listener
}
