package org.m0skit0.android.huawei.testapp.demos

import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.m0skit0.android.huawei.iap.huaweiIsIAPEnvironmentReadyMaybe
import org.m0skit0.android.huawei.testapp.ToastLogActivity

class IapDemoActivity : ToastLogActivity(), CoroutineScope by CoroutineScope(Dispatchers.Default) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadProducts()
    }

    private fun loadProducts() {
        launch {
            huaweiIsIAPEnvironmentReadyMaybe().map { isEnvReady ->
                if (isEnvReady.returnCode == 0) {

                }
            }
        }
    }
}
