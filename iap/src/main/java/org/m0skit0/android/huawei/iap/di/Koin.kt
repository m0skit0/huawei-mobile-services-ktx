package org.m0skit0.android.huawei.iap.di

import android.app.Application
import org.koin.core.context.loadKoinModules
import org.m0skit0.android.huawei.core.di.KoinModuleProvider

private val modules = listOf<KoinModuleProvider>(
    IAPModuleProvider
).map { it.module() }

internal fun Application.loadKoin() {
    loadKoinModules(modules)
}
