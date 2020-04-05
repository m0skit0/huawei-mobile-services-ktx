package org.m0skit0.android.huawei.push

import android.app.Application
import org.m0skit0.android.huawei.core.huaweiLoadCore
import org.m0skit0.android.huawei.push.di.loadKoin

fun Application.huaweiLoadPush() {
    huaweiLoadCore()
    loadKoin()
}
