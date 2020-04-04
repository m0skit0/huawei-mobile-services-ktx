package org.m0skit0.android.huawei.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

private val modules = listOf<KoinModuleProvider>(
    AGCModuleProvider
).map { it.module() }

internal fun Application.loadKoin() {
    startKoin {
        androidContext(this@loadKoin)
        loadKoinModules(modules)
    }
}

internal fun koin(): Koin = KoinContextHandler.get()
