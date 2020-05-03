package org.m0skit0.android.huawei.testapp

internal const val LOG_TAG = "[HMS KTX]"

internal interface Logger {
    fun <T> T.log()
    fun log(message: String)
    fun Throwable.log(message: String = "ERROR")
}
