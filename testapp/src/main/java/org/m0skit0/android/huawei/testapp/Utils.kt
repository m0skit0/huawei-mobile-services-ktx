package org.m0skit0.android.huawei.testapp

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

internal fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

internal fun Activity.toast(@StringRes id: Int) {
    Toast.makeText(this, id, Toast.LENGTH_LONG).show()
}
