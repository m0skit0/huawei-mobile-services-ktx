package org.m0skit0.android.huawei.core.agc

import org.m0skit0.android.huawei.core.di.AGCModuleProvider.NAMED_HUAWEI_APP_ID
import org.m0skit0.android.huawei.core.di.koin

suspend fun huaweiAppId(): String = koin().get(NAMED_HUAWEI_APP_ID)
