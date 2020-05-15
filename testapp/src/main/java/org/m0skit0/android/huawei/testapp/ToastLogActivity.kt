package org.m0skit0.android.huawei.testapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("Registered")
open class ToastLogActivity : AppCompatActivity(), Logger {

    override fun <T> T.log() {
        DefaultLogger.log()
        toast(toString())
    }

    override fun log(message: String) {
        DefaultLogger.log(message)
        toast(message)
    }

    override fun Throwable.log(message: String) {
        DefaultLogger.run { this@log.log(message) }
        toast("$message: ${javaClass.simpleName}: ${this.message}")
    }
}
