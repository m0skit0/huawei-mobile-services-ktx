package org.m0skit0.android.huawei.ads

import com.huawei.hms.ads.AdParam
import com.huawei.hms.ads.nativead.NativeAdLoader

private val AD_COUNT_RANGE = (1..5)

fun NativeAdLoader.load(param: AdParam = AdParam.Builder().build(), count: Int = 1): NativeAdLoader = apply {
    if (count !in AD_COUNT_RANGE) throw IllegalArgumentException("Count must be in the range $AD_COUNT_RANGE")
    if (count == 1) {
        loadAd(param)
    } else {
        loadAds(param, count)
    }
}
