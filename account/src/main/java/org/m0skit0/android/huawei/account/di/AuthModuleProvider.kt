package org.m0skit0.android.huawei.account.di

import com.huawei.hms.support.hwid.HuaweiIdAuthManager
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper
import com.huawei.hms.support.hwid.service.HuaweiIdAuthService
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.huawei.core.di.KoinModuleProvider

internal object AuthModuleProvider : KoinModuleProvider {

    val NAMED_AUTH_SERVICE = named("NAMED_AUTH_SERVICE")

    override fun module(): Module = module {

        factory { HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM) }

        factory<(HuaweiIdAuthParams) -> HuaweiIdAuthService>(NAMED_AUTH_SERVICE) {
            { params -> HuaweiIdAuthManager.getService(get(), params) }
        }
    }
}
