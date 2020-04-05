package org.m0skit0.android.huawei.account.deserialization

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import org.m0skit0.android.huawei.account.model.JWTCertificateKeys

internal object JWTCertificateKeysDeserializer : ResponseDeserializable<JWTCertificateKeys> {
    override fun deserialize(content: String): JWTCertificateKeys? =
        Gson().fromJson(content, JWTCertificateKeys::class.java)
}
