package org.m0skit0.android.huawei.push.di

import com.huawei.hms.push.HmsMessaging
import org.koin.core.module.Module
import org.koin.dsl.module
import org.m0skit0.android.huawei.core.di.KoinModuleProvider

internal object HMSModule : KoinModuleProvider {
    override fun module(): Module = module {
        factory { HmsMessaging.getInstance(get()) }
    }
}
