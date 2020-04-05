package org.m0skit0.android.huawei.account.deserialization

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import org.m0skit0.android.huawei.account.model.OpenIdConfiguration

internal object OpenIdConfigurationDeserializer : ResponseDeserializable<OpenIdConfiguration> {
    override fun deserialize(content: String): OpenIdConfiguration? =
        Gson().fromJson(content, OpenIdConfiguration::class.java)
}
