package org.m0skit0.android.huawei.testapp

import android.app.Application
import org.m0skit0.android.huawei.account.huaweiLoadAccount
import org.m0skit0.android.huawei.iap.huaweiLoadIAP
import org.m0skit0.android.huawei.push.huaweiLoadPush

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        loadHuawei()
    }

    private fun loadHuawei() {
        huaweiLoadPush()
        huaweiLoadIAP()
        huaweiLoadAccount()
    }
}
