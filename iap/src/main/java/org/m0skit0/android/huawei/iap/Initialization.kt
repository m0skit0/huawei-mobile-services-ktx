package org.m0skit0.android.huawei.iap

import android.app.Application
import org.m0skit0.android.huawei.core.huaweiLoadCore
import org.m0skit0.android.huawei.iap.di.loadKoin

fun Application.huaweiLoadIAP() {
    huaweiLoadCore()
    loadKoin()
}
