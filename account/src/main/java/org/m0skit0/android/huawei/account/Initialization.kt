package org.m0skit0.android.huawei.account

import android.app.Application
import org.m0skit0.android.huawei.account.di.loadKoin
import org.m0skit0.android.huawei.core.huaweiLoadCore

fun Application.huaweiLoadAccount() {
    huaweiLoadCore()
    loadKoin()
}
