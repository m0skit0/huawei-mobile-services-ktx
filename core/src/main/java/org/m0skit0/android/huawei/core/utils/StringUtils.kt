package org.m0skit0.android.huawei.core.utils

import android.util.Base64

fun String.decodeBase64() = Base64.decode(this, Base64.NO_WRAP)
