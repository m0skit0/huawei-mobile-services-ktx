package org.m0skit0.android.huawei.testapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import org.m0skit0.android.huawei.testapp.demos.IapDemoActivity
import org.m0skit0.android.huawei.testapp.demos.PushDemoActivity

class MainActivity : AppCompatActivity() {

    private val demoMap = mapOf(
        R.id.pushDemoButton to PushDemoActivity::class.java,
        R.id.iapDemoButton to IapDemoActivity::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        demoMap.keys.forEach { id ->
            findViewById<Button>(id).setOnClickListener { launchDemoActivity(id) }
        }
    }

    private fun launchDemoActivity(@IdRes id: Int) {
        demoMap[id]?.let { `class` ->
            Intent(this@MainActivity, `class`).run {
                startActivity(this)
            }
        }
    }
}
