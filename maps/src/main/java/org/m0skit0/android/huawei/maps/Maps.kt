package org.m0skit0.android.huawei.maps

import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.SupportMapFragment
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun MapView.getMap(): HuaweiMap =
    suspendCoroutine { continuation ->
        getMapAsync { continuation.resume(it) }
    }

suspend fun SupportMapFragment.getMap(): HuaweiMap =
    suspendCoroutine { continuation ->
        getMapAsync { continuation.resume(it) }
    }
