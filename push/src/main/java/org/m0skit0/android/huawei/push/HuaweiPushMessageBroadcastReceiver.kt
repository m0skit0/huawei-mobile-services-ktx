package org.m0skit0.android.huawei.push

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.huawei.hms.push.RemoteMessage

class HuaweiPushMessageBroadcastReceiver(private val receiver: HuaweiPushMessageReceiver) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.run {
            when (action) {
                HuaweiPushMessageService.INTENT_ACTION_MESSAGE ->
                    getParcelableExtra<RemoteMessage>(HuaweiPushMessageService.MESSAGE_KEY)?.let { remoteMessage ->
                        receiver.onHuaweiPushMessageReceived(remoteMessage)
                    }
                HuaweiPushMessageService.INTENT_ACTION_TOKEN ->
                    getStringExtra(HuaweiPushMessageService.TOKEN_KEY)?.let { token ->
                        receiver.onHuaweiNewTokenReceived(token)
                    }
                else -> {}
            }
        }
    }
}
