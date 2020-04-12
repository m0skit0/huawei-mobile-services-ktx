package org.m0skit0.android.huawei.account

import arrow.core.Either
import arrow.fx.IO
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitObject
import com.huawei.hms.support.hwid.result.AuthHuaweiId
import io.jsonwebtoken.Jwts
import kotlinx.coroutines.runBlocking
import org.m0skit0.android.huawei.account.di.DeserializationModuleProvider.NAMED_DESERIALIZE_JWT_CERTIFICATE_KEYS
import org.m0skit0.android.huawei.account.di.DeserializationModuleProvider.NAMED_DESERIALIZE_OPEN_ID_CONFIGURATION
import org.m0skit0.android.huawei.account.model.JWTCertificateKey
import org.m0skit0.android.huawei.account.model.JWTCertificateKeys
import org.m0skit0.android.huawei.account.model.OpenIdConfiguration
import org.m0skit0.android.huawei.core.utils.koin

internal fun openIdConfigurationMaybe(): IO<OpenIdConfiguration> =
    IO {
        Fuel.get(BuildConfig.HUAWEI_OPENID_CONFIGURATION_URL)
            .awaitObject<OpenIdConfiguration>(koin().get(NAMED_DESERIALIZE_OPEN_ID_CONFIGURATION))
    }

internal fun publicKeyMaybe(url: String): IO<JWTCertificateKey> =
    IO {
        Fuel.get(url)
            .awaitObject<JWTCertificateKeys>(koin().get(NAMED_DESERIALIZE_JWT_CERTIFICATE_KEYS))
            .keys[0]
    }

internal suspend fun String.verify(key: JWTCertificateKey): Either<Throwable, String> =
    Either.catch {
        apply {
            Jwts.parserBuilder().setSigningKey(key.n).build().parseClaimsJwt(this)
        }
    }

suspend fun AuthHuaweiId.verify(): IO<AuthHuaweiId> =
    openIdConfigurationMaybe().flatMap { openIdConfiguration ->
        publicKeyMaybe(openIdConfiguration.jwks_uri)
    }.map { jwtCertificateKey ->
        runBlocking { accessToken.verify(jwtCertificateKey) }
    }.map { this  }
