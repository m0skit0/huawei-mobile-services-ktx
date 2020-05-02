package org.m0skit0.android.huawei.core.di

import com.huawei.agconnect.config.AGConnectServicesConfig
import org.koin.core.KoinComponent
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal object AGCModuleProvider : KoinModuleProvider, KoinComponent {

    val NAMED_HUAWEI_APP_ID = named("NAMED_HUAWEI_APP_ID")

    override fun module(): Module = module {

        factory<AGConnectServicesConfig>(override = true) { AGConnectServicesConfig.fromContext(get()) }

        single(NAMED_HUAWEI_APP_ID, override = true) {
            get<AGConnectServicesConfig>().getString("client/app_id")
        }
    }
}
