package org.m0skit0.android.huawei.iap.di

import com.huawei.hms.iap.Iap
import org.koin.core.module.Module
import org.koin.dsl.module
import org.m0skit0.android.huawei.core.di.KoinModuleProvider

internal object IAPModuleProvider : KoinModuleProvider {
    override fun module(): Module = module {
        factory { Iap.getIapClient(get()) }
    }
}
