package org.m0skit0.android.huawei.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException

private val modules = listOf<KoinModuleProvider>(
    AGCModuleProvider,
    HMSModuleProvider
).map { it.module() }

internal fun Application.loadKoin() {
    try {
        // In case importing app doesn't use Koin we need to start it
        startKoin {
            androidContext(this@loadKoin)
        }
    } catch (e: KoinAppAlreadyStartedException) {
        // Koin module already started, continue loading our modules.
    }
    loadKoinModules(modules)
}
