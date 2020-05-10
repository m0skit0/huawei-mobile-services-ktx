package org.m0skit0.android.huawei.push

import com.huawei.hms.push.RemoteMessage

interface HuaweiPushMessageReceiver {
    fun onHuaweiPushMessageReceived(remoteMessage: RemoteMessage)
    fun onHuaweiNewTokenReceived(token: String)
}
