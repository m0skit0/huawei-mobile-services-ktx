package org.m0skit0.android.huawei.core.di

import com.huawei.hms.aaid.HmsInstanceId
import org.koin.core.module.Module
import org.koin.dsl.module

internal object HMSModuleProvider : KoinModuleProvider {

    override fun module(): Module = module {
        factory<HmsInstanceId>(override = true) { HmsInstanceId.getInstance(get()) }
    }
}
