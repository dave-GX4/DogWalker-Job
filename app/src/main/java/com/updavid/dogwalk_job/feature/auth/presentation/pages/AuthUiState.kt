package com.updavid.dogwalk_user.feature.auth.presentation.pages

data class AuthUiState(
    val loginEmail: String = "",
    val loginPassword: String = "",
    // Errores de Login
    val loginEmailError: String? = null,
    val loginPasswordError: String? = null,
)