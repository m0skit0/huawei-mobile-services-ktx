package org.m0skit0.android.huawei.push

import org.m0skit0.android.huawei.core.hms.huaweiHMSToken

suspend fun huaweiPushToken(): String = huaweiHMSToken("HCM")
