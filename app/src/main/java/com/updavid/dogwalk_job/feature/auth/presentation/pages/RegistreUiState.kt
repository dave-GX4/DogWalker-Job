package com.updavid.dogwalk_job.feature.auth.presentation.pages

data class RegistreUiState(
    val registerName: String = "",
    val registerEmail: String = "",
    val registerPhone: String = "",
    val registerAddress: String = "",
    val registerPassword: String = "",
    val registerConfirmation: String = "",
    val description: String = "",
    val isTermsAcceptedUser: Boolean = false,
    val isTermsAcceptedJob: Boolean = false,

    // Errores de Registro (validaciones estrictas)
    val registerNameError: String? = null,
    val registerEmailError: String? = null,
    val registerPhoneError: String? = null,
    val registerAddressError: String? = null,
    val registerPasswordError: String? = null,
    val registerConfirmationError: String? = null,
    val descriptionError: String? = null
)