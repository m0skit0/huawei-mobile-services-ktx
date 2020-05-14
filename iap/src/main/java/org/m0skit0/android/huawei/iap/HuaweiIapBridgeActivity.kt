package org.m0skit0.android.huawei.iap

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.huawei.hms.iap.entity.OrderStatusCode
import com.huawei.hms.iap.entity.PurchaseResultInfo
import com.huawei.hms.support.api.client.Status
import org.koin.core.KoinComponent
import org.koin.core.get
import kotlin.random.Random

class HuaweiIapBridgeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        status.startResolutionForResult(this, PURCHASE_INTENT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        intent?.run {
            when (requestCode) {
                PURCHASE_INTENT_CODE -> parsePurchaseResult().checkResultAndCallListener()
            }
        }
        finish()
    }

    private fun PurchaseResultInfo.checkResultAndCallListener() {
        when (returnCode) {
            OrderStatusCode.ORDER_STATE_SUCCESS -> onSuccess()
            OrderStatusCode.ORDER_STATE_CANCEL -> HuaweiIapCancel.onFailure()
            OrderStatusCode.ORDER_PRODUCT_OWNED -> HuaweiIapProductOwned.onFailure()
            else -> HuaweiIapFailed(returnCode).onFailure()
        }
    }

    companion object : KoinComponent {
        internal val PURCHASE_INTENT_CODE: Int by lazy { Random.nextInt() and 0x0000FFFF }

        private lateinit var status: Status
        private lateinit var onSuccess: PurchaseResultInfo.() -> Unit
        private lateinit var onFailure: Throwable.() -> Unit

        fun start(status: Status, onFailure: Throwable.() -> Unit, onSuccess: PurchaseResultInfo.() -> Unit) {
            HuaweiIapBridgeActivity.status = status
            HuaweiIapBridgeActivity.onSuccess = onSuccess
            HuaweiIapBridgeActivity.onFailure = onFailure
            Intent(get(), HuaweiIapBridgeActivity::class.java).let { intent ->
                get<Context>().startActivity(intent)
            }
        }
    }
}
