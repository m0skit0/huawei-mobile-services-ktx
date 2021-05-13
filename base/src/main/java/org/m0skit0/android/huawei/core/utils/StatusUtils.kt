package org.m0skit0.android.huawei.core.utils

import android.app.Activity
import com.huawei.hms.support.api.client.Status

inline fun Status.startResolutionIfAvailable(activity: Activity, resultCode: Int, notAvailable: () -> Unit = {}) {
    if (hasResolution()) {
        startResolutionForResult(activity, resultCode)
    } else notAvailable()
}
