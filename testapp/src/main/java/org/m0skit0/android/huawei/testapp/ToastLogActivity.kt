package org.m0skit0.android.huawei.testapp

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class ToastLogActivity : AppCompatActivity(), Logger {

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

    protected fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    protected fun toast(@StringRes id: Int) {
        Toast.makeText(this, id, Toast.LENGTH_LONG).show()
    }
}
