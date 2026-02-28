package com.updavid.dogwalk_user.feature.auth.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.updavid.dogwalk_user.feature.auth.domain.usecases.PostSingInUseCase
import com.updavid.dogwalk_user.feature.auth.presentation.pages.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val postSingInUseCase: PostSingInUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    fun onLoginEmailChanged(email: String) {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val error = if (email.isNotEmpty() && !isValid) "Email inválido" else null

        _uiState.update {
            it.copy(loginEmail = email, loginEmailError = error)
        }
    }

    fun onLoginPasswordChanged(password: String) {
        val error = when {
            password.isEmpty() -> null
            password.length < 8 -> "Mínimo 8 caracteres"
            password.none { it.isUpperCase() } -> "Falta mayúscula"
            password.none { it.isDigit() } -> "Falta número"
            else -> null
        }

        _uiState.update {
            it.copy(loginPassword = password, loginPasswordError = error)
        }
    }

    fun onAuthentication() {
        val state = _uiState.value
        // Validar campos vacíos antes de enviar a API
        if(state.loginEmail.isBlank() || state.loginPassword.isBlank()){
            _uiState.update { it.copy(loginEmailError = "Requerido", loginPasswordError = "Requerido") }
            return
        }
        println("API LOGIN: ${state.loginEmail}")
    }
}