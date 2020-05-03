package org.m0skit0.android.huawei.testapp

import android.util.Log

internal object DefaultLogger : Logger {

    override fun <T> T.log() {
        Log.d(LOG_TAG, toString())
    }

    override fun log(message: String) {
        Log.d(LOG_TAG, message)
    }

    override fun Throwable.log(message: String) {
        Log.e(LOG_TAG, message, this)
    }
}
