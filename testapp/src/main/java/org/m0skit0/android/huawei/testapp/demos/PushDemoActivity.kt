package org.m0skit0.android.huawei.testapp.demos

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.TextView
import com.huawei.hms.push.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.m0skit0.android.huawei.push.huaweiPushTokenMaybe
import org.m0skit0.android.huawei.testapp.R
import org.m0skit0.android.huawei.testapp.ToastLogActivity

class PushDemoActivity : ToastLogActivity(), CoroutineScope by CoroutineScope(Dispatchers.Default) {

    private inner class PushMessageReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.run {
                when (action) {
                    PushMessageService.INTENT_ACTION_TOKEN -> showHCMToken()
                    PushMessageService.INTENT_ACTION_MESSAGE -> showRemoteMessage()
                    else -> log("Unknown action $action")
                }
            }
        }
    }

    private var receiver: PushMessageReceiver? = null

    override fun onResume() {
        super.onResume()
        receiver = PushMessageReceiver()
        registerReceiver(receiver, IntentFilter())
        initializePush()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    private fun Intent.showRemoteMessage() {
        val message = getParcelableExtra<RemoteMessage>(PushMessageService.MESSAGE_KEY)
        findViewById<TextView>(R.id.hcmDataValue).text = message?.data ?: getString(R.string.empty)
    }

    private fun Intent.showHCMToken() {
        val token = getStringExtra(PushMessageService.TOKEN_KEY) ?: "NULL"
        showHCMToken(token)
    }

    private fun showHCMToken(token: String) {
        findViewById<TextView>(R.id.hcmTokenValue).text = token
    }

    private fun initializePush() {
        launch {
            huaweiPushTokenMaybe().map { token ->
                log("HCM token: $token")
                showHCMToken(token)
            }
        }
    }
}
