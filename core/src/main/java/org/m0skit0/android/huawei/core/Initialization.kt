package org.m0skit0.android.huawei.core

import android.app.Application
import org.m0skit0.android.huawei.core.di.loadKoin

fun Application.huaweiLoadCore() {
    loadKoin()
}
