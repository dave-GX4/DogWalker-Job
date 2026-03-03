package com.updavid.dogwalk_job.feature.auth.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.updavid.dogwalk_job.feature.auth.presentation.screens.RegistreUiState
import com.updavid.dogwalk_job.feature.auth.presentation.screens.UiEvents
import com.updavid.dogwalk_user.feature.auth.domain.entitie.WorkerRegistration
import com.updavid.dogwalk_user.feature.auth.domain.usecases.PostSingUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistreViewModel @Inject constructor(
    private val postSingUpUseCase: PostSingUpUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(RegistreUiState())
    val uiState = _uiState.asStateFlow()
    private val _experienceOptions = listOf("Menos de 1 año", "1-3 años", "3-5 años", "Más de 5 años")
    val experienceOptions = _experienceOptions

    private val _serviceTypesMap = mapOf(
        "Movilidad & Ejercicio" to listOf("Paseo de perros", "Running con perros", "Transporte de mascotas", "Aventura outdoor"),
        "Cuidado en Hogar" to listOf("Cuidado en casa del dueño", "Cuidado en casa del cuidador", "Visitas a domicilio", "Compañía para mascota anciana"),
        "Higiene & Estética" to listOf("Baño básico a domicilio", "Grooming completo", "Spa y bienestar", "Limpieza dental"),
        "Entrenamiento & Comportamiento" to listOf("Entrenamiento básico", "Entrenamiento avanzado", "Modificación de conducta", "Socialización guiada", "Entrenamiento para cachorros"),
        "Salud & Bienestar" to listOf("Visita al veterinario", "Fisioterapia canina", "Alimentación especializada"),
        "Extras" to listOf("Fotografía de mascotas", "Cumpleaños/eventos para mascotas", "Entrega de alimentos/suministros")
    )
    val serviceTypesMap = _serviceTypesMap

    private val _requiresCertificateList = listOf(
        "Transporte de mascotas", "Cuidado en casa del cuidador", "Compañía para mascota anciana",
        "Grooming completo", "Spa y bienestar", "Limpieza dental", "Entrenamiento avanzado",
        "Modificación de conducta", "Socialización guiada", "Visita al veterinario",
        "Fisioterapia canina", "Alimentación especializada", "Entrega de alimentos/suministros"
    )
    val requiresCertificateList = _requiresCertificateList

    private val _eventChannel = Channel< UiEvents>()
    val events = _eventChannel.receiveAsFlow()

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

    fun onInServiceChanged(isActive: Boolean) {
        _uiState.update { it.copy(inService = isActive) }
    }

    fun onBackgroundCheckChanged(isAuthorized: Boolean) {
        _uiState.update { it.copy(backgroundCheck = isAuthorized) }
    }

    fun onExperienceChanged(experience: String) {
        _uiState.update { it.copy(experience = experience) }
    }

    fun onServiceTypeChanged(type: String) {
        _uiState.update {
            it.copy(
                selectedType = type,
                selectedService = ""
            )
        }
    }

    fun onServiceChanged(service: String) {
        _uiState.update { it.copy(selectedService = service) }
    }

    fun onAuthentication() {
        val state = _uiState.value
        if (state.registerNameError != null || state.registerEmailError != null ||
            state.registerPasswordError != null || state.descriptionError != null ||
            state.experience.isEmpty() || state.selectedService.isEmpty()) {
            return
        }

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val workerData = WorkerRegistration(
                name = state.registerName,
                email = state.registerEmail,
                phone = state.registerPhone,
                address = state.registerAddress,
                password = state.registerPassword,

                description = state.description,
                experience = state.experience,
                inService = state.inService,
                backgroundCheck = state.backgroundCheck,

                serviceCategory = state.selectedType,
                specificService = state.selectedService
            )
            val result = postSingUpUseCase(workerData)

            _uiState.update { it.copy(isLoading = false) }

            result.onSuccess {
                println("REGISTRO EXITOSO")
                _uiState.value = RegistreUiState()
                _eventChannel.send(UiEvents.NavigateToMap)

            }.onFailure { exception ->
                val errorMsg = exception.message ?: "Ocurrió un error inesperado"
                println("ERROR AL REGISTRAR: $errorMsg")

                _eventChannel.send(UiEvents.ShowError(errorMsg))
            }
        }
    }
}