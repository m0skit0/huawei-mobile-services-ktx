package org.m0skit0.android.huawei.core.hms

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.m0skit0.android.huawei.core.di.HMSModuleProvider.NAMED_HUAWEI_TOKEN
import org.m0skit0.android.huawei.core.di.koin

/**
 * For now this only supports EMUI 10.0 or higher.
 * EMUI < 10.0 uses callback for the token.
 * See https://developer.huawei.com/consumer/en/doc/development/HMS-Guides/push-basic-capability
 */
suspend fun huaweiHMSToken(type: String): String = withContext(Dispatchers.Main) {
    koin().get<(String) -> String>(NAMED_HUAWEI_TOKEN)(type)
}
