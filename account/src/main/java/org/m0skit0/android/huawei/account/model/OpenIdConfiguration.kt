package org.m0skit0.android.huawei.account.model

internal data class OpenIdConfiguration(
    val authorization_endpoint: String,
    val claims_supported: List<String>,
    val code_challenge_methods_supported: List<String>,
    val id_token_signing_alg_values_supported: List<String>,
    val issuer: String,
    val jwks_uri: String,
    val response_types_supported: List<String>,
    val revocation_endpoint: String,
    val scopes_supported: List<String>,
    val subject_types_supported: List<String>,
    val token_endpoint: String,
    val token_endpoint_auth_methods_supported: List<String>
)
