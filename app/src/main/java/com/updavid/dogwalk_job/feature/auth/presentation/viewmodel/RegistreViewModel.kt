package com.updavid.dogwalk_job.feature.auth.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.updavid.dogwalk_job.feature.auth.presentation.pages.RegistreUiState
import com.updavid.dogwalk_user.feature.auth.domain.usecases.PostSingUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegistreViewModel @Inject constructor(
    private val postSingUpUseCase: PostSingUpUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(RegistreUiState())
    val uiState = _uiState.asStateFlow()

    //validaciones de SingUp
    fun onRegisterNameChanged(name: String) {
        val hasUpperCase = name.any { it.isUpperCase() }
        val hasNoNumbers = name.none { it.isDigit() }

        val error = when {
            name.isEmpty() -> null
            !hasUpperCase -> "Falta mayúscula"
            !hasNoNumbers -> "No debe tener números"
            else -> null
        }
        _uiState.update {
            it.copy(
                registerName = name,
                registerNameError = error
            )
        }
    }

    fun onRegisterEmailChanged(email: String) {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val error = if (email.isNotEmpty() && !isValid) "Email inválido" else null
        _uiState.update {
            it.copy(
                registerEmail = email,
                registerEmailError = error
            )
        }
    }

    fun onRegisterPhoneChanged(phone: String) {
        val isValidFormat = phone.all { it.isDigit() || it == '+' }

        val error = when {
            phone.isEmpty() -> null
            !isValidFormat -> "Solo se permiten números"
            phone.length < 9 -> "Número muy corto"
            else -> null
        }
        _uiState.update {
            it.copy(
                registerPhone = phone,
                registerPhoneError = error
            )
        }
    }

    fun onRegisterAddressChanged(address: String) {
        val error = when {
            address.isEmpty() -> null
            address.length < 5 -> "Dirección muy corta"
            else -> null
        }
        _uiState.update {
            it.copy(
                registerAddress = address,
                registerAddressError = error
            )
        }
    }

    fun onTermsAcceptedUserChanged(isAccepted: Boolean) {
        _uiState.update { it.copy(isTermsAcceptedUser = isAccepted) }
    }

    // Para el Paso 2 (Trabajo)
    fun onTermsAcceptedJobChanged(isAccepted: Boolean) {
        _uiState.update { it.copy(isTermsAcceptedJob = isAccepted) }
    }

    fun onRegisterPasswordChanged(password: String) {
        val error = when {
            password.isEmpty() -> null
            password.length < 8 -> "Mínimo 8 caracteres"
            password.none { it.isUpperCase() } -> "Falta mayúscula"
            password.none { it.isDigit() } -> "Falta número"
            else -> null
        }

        val confirmError = if (uiState.value.registerConfirmation.isNotEmpty() &&
            uiState.value.registerConfirmation != password) "No coinciden" else null

        _uiState.update {
            it.copy(
                registerPassword = password,
                registerPasswordError = error,
                registerConfirmationError = confirmError
            )
        }
    }

    fun onRegisterConfirmationChanged(confirmation: String) {
        val error = if (uiState.value.registerPassword != confirmation) "No coinciden" else null
        _uiState.update {
            it.copy(
                registerConfirmation = confirmation,
                registerConfirmationError = error
            )
        }
    }

    fun onDescriptionChanged(description: String){
        val error = when {
            description.isEmpty() -> null
            !description.first().isUpperCase() -> "Falta mayúscula al inicio"
            description.length < 10 -> "La descripción debe tener al menos 10 caracteres"
            else -> null
        }

        _uiState.update {
            it.copy(
                description = description,
                descriptionError = error
            )
        }
    }

    fun onAuthentication() {
        val state = _uiState.value
        // Verificar que no haya errores antes de enviar
        if (state.registerNameError != null || state.registerEmailError != null || state.registerPasswordError != null) return

        println("API REGISTER: ${state.registerEmail}")
    }
}