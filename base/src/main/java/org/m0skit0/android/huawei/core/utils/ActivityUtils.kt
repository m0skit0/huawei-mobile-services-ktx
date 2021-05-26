package org.m0skit0.android.huawei.core.utils

import android.app.Activity
import android.content.DialogInterface
import com.huawei.hms.api.HuaweiApiAvailability

fun Activity.showErrorDialog(errorCode: Int, requestCode: Int, onCancel: (DialogInterface) -> Unit = {}) {
    HuaweiApiAvailability.getInstance().showErrorDialogFragment(this, errorCode, requestCode, onCancel)
}
