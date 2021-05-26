package org.m0skit0.android.huawei.core.utils

import com.huawei.hms.api.HuaweiApiAvailability

fun Int.toErrorString(): String = HuaweiApiAvailability.getInstance().getErrorString(this)
