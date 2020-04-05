package org.m0skit0.android.huawei.core.di

import android.app.Application
import arrow.core.Either
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

private val modules = listOf<KoinModuleProvider>(
    AGCModuleProvider,
    HMSModuleProvider
).map { it.module() }

internal fun Application.loadKoin() {
    runBlocking {
        Either.catch {
            startKoin {
                androidContext(this@loadKoin)
                loadKoinModules(modules)
            }
        }
    }
}
