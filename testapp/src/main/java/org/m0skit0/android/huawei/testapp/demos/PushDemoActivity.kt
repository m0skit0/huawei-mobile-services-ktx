package org.m0skit0.android.huawei.testapp.demos

import android.content.IntentFilter
import android.widget.TextView
import com.huawei.hms.push.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.m0skit0.android.huawei.push.HuaweiPushMessageBroadcastReceiver
import org.m0skit0.android.huawei.push.HuaweiPushMessageReceiver
import org.m0skit0.android.huawei.push.huaweiPushTokenMaybe
import org.m0skit0.android.huawei.testapp.R
import org.m0skit0.android.huawei.testapp.ToastLogActivity

class PushDemoActivity : ToastLogActivity(),
    HuaweiPushMessageReceiver,
    CoroutineScope by CoroutineScope(Dispatchers.Default) {

    private var receiver = HuaweiPushMessageBroadcastReceiver(this)

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter())
        initializePush()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    private fun initializePush() {
        launch {
            huaweiPushTokenMaybe().map { token ->
                log("HCM token: $token")
                showHCMToken(token)
            }
        }
    }

    private fun showHCMToken(token: String) {
        findViewById<TextView>(R.id.hcmTokenValue).text = token
    }

    override fun onHuaweiPushMessageReceived(remoteMessage: RemoteMessage) {
        findViewById<TextView>(R.id.hcmDataValue).text = remoteMessage.data
    }

    override fun onHuaweiNewTokenReceived(token: String) {
        findViewById<TextView>(R.id.hcmTokenValue).text = token
    }
}
