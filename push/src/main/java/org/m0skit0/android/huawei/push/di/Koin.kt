package org.m0skit0.android.huawei.push.di

import android.app.Application
import org.koin.core.context.loadKoinModules

private val modules = listOf(
    HMSModule
).map { it.module() }

internal fun Application.loadKoin() {
    loadKoinModules(modules)
}
