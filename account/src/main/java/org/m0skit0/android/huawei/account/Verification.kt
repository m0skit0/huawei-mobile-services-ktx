package org.m0skit0.android.huawei.account

import arrow.core.Either
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObject
import io.jsonwebtoken.Jwts
import org.m0skit0.android.huawei.account.di.DeserializationModuleProvider.NAMED_DESERIALIZE_JWT_CERTIFICATE_KEYS
import org.m0skit0.android.huawei.account.di.DeserializationModuleProvider.NAMED_DESERIALIZE_OPEN_ID_CONFIGURATION
import org.m0skit0.android.huawei.account.model.JWTCertificateKey
import org.m0skit0.android.huawei.account.model.JWTCertificateKeys
import org.m0skit0.android.huawei.account.model.OpenIdConfiguration
import org.m0skit0.android.huawei.core.utils.koin

private suspend fun openIdConfigurationMaybe(): Either<Throwable, OpenIdConfiguration> =
    Either.catch {
        Fuel.get(BuildConfig.HUAWEI_OPENID_CONFIGURATION_URL)
            .awaitObject(koin().get(NAMED_DESERIALIZE_OPEN_ID_CONFIGURATION))
    }

private suspend fun publicKeyMaybe(url: String): Either<Throwable, JWTCertificateKey> =
    Either.catch {
        Fuel.get(url)
            .awaitObject<JWTCertificateKeys>(koin().get(NAMED_DESERIALIZE_JWT_CERTIFICATE_KEYS))
            .keys[0]
    }

private suspend fun String.verify(key: JWTCertificateKey): Either<Throwable, String> =
    Either.catch {
        apply {
            Jwts.parserBuilder().setSigningKey(key.n).build().parseClaimsJwt(this)
        }
    }
