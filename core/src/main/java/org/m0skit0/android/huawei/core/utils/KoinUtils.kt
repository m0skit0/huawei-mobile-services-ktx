package org.m0skit0.android.huawei.core.utils

import org.koin.core.Koin
import org.koin.core.context.KoinContextHandler

fun koin(): Koin = KoinContextHandler.get()
