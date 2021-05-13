package org.m0skit0.android.huawei.core.utils

import android.content.Context
import com.huawei.hms.api.HuaweiApiAvailability

val Context.isHuaweiMobileServicesAvailable: Boolean
    get() = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this) == 0

val Context.isHuaweiMobileServicesOutOfDate: Boolean
    get() = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this) == 2
