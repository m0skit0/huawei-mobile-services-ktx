package org.m0skit0.android.huawei.account.di

import com.github.kittinunf.fuel.core.ResponseDeserializable
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.huawei.account.deserialization.JWTCertificateKeysDeserializer
import org.m0skit0.android.huawei.account.deserialization.OpenIdConfigurationDeserializer
import org.m0skit0.android.huawei.account.model.JWTCertificateKeys
import org.m0skit0.android.huawei.account.model.OpenIdConfiguration
import org.m0skit0.android.huawei.core.di.KoinModuleProvider

internal object DeserializationModuleProvider : KoinModuleProvider {

    val NAMED_DESERIALIZE_OPEN_ID_CONFIGURATION = named("NAMED_DESERIALIZE_OPEN_ID_CONFIGURATION")
    val NAMED_DESERIALIZE_JWT_CERTIFICATE_KEYS = named("NAMED_DESERIALIZE_JWT_CERTIFICATE_KEYS")

    override fun module(): Module = module {

        single<ResponseDeserializable<OpenIdConfiguration>>(NAMED_DESERIALIZE_OPEN_ID_CONFIGURATION) {
            OpenIdConfigurationDeserializer
        }

        single<ResponseDeserializable<JWTCertificateKeys>>(NAMED_DESERIALIZE_JWT_CERTIFICATE_KEYS) {
            JWTCertificateKeysDeserializer
        }
    }
}
