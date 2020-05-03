package org.m0skit0.android.huawei.testapp.demos

import android.content.Intent
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import org.m0skit0.android.huawei.testapp.DefaultLogger
import org.m0skit0.android.huawei.testapp.Logger

class PushMessageService : HmsMessageService(), Logger by DefaultLogger {

    companion object {
        internal const val INTENT_ACTION_MESSAGE = "INTENT_ACTION_MESSAGE"
        internal const val MESSAGE_KEY = "MESSAGE_KEY"
        internal const val INTENT_ACTION_TOKEN = "INTENT_ACTION_TOKEN"
        internal const val TOKEN_KEY = "TOKEN_KEY"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        remoteMessage?.run {
            Intent(INTENT_ACTION_MESSAGE).putExtra(
                MESSAGE_KEY, this).broadcast()
        }
    }

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        token?.run {
            Intent(INTENT_ACTION_TOKEN).putExtra(
                TOKEN_KEY, this).broadcast()
        }
    }

    private fun Intent.broadcast() {
        sendBroadcast(this)
    }
}
