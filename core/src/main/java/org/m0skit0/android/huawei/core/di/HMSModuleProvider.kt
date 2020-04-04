package org.m0skit0.android.huawei.core.di

import com.huawei.hms.aaid.HmsInstanceId
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.huawei.core.di.AGCModuleProvider.NAMED_HUAWEI_APP_ID

internal object HMSModuleProvider : KoinModuleProvider {

    val NAMED_HUAWEI_TOKEN = named("NAMED_HUAWEI_TOKEN")

    override fun module(): Module = module {

        factory<HmsInstanceId> { HmsInstanceId.getInstance(get()) }

        factory<(String) -> String>(NAMED_HUAWEI_TOKEN) {
            { type -> get<HmsInstanceId> ().getToken(get(NAMED_HUAWEI_APP_ID), type); }
        }
    }
}
