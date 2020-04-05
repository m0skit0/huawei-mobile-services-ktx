package org.m0skit0.android.huawei.account.model

internal data class JWTCertificateKeys(
    val keys: List<JWTCertificateKey>
)

data class JWTCertificateKey(
    val alg: String,
    val e: String,
    val kid: String,
    val kty: String,
    val n: String,
    val use: String
)
