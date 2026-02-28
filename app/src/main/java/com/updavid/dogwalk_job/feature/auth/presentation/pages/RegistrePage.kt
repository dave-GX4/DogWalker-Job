package com.updavid.dogwalk_job.feature.auth.presentation.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.updavid.dogwalk_job.feature.auth.presentation.conponents.Registre.Step1
import com.updavid.dogwalk_job.feature.auth.presentation.conponents.Registre.Step2
import com.updavid.dogwalk_job.feature.auth.presentation.conponents.Registre.Step3
import com.updavid.dogwalk_job.feature.auth.presentation.viewmodel.RegistreViewModel

@Composable
fun RegistrePage(
    viewModel: RegistreViewModel,
    onBack: () -> Unit
){
    val PrimaryGreen = Color(0xFF13EC5B)
    val BackgroundLight = Color(0xFFF6F8F6)
    var currentStep by remember { mutableIntStateOf(1) }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
            .systemBarsPadding()
    ) {
        // --- TOP BAR ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { if (currentStep > 1) currentStep-- }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
            Text(
                text = "Registro de Cuidador",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(48.dp)) // Balancear el título
        }

        // --- PROGRESS BAR ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Paso $currentStep de 3", fontWeight = FontWeight.Medium, fontSize = 14.sp)
                val stepName = when(currentStep) {
                    1 -> "Información Personal"
                    2 -> "Perfil Profesional"
                    else -> "Detalles Finales"
                }
                Text(stepName, color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = currentStep / 3f,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = PrimaryGreen,
                trackColor = PrimaryGreen.copy(alpha = 0.2f)
            )
        }

        // --- CONTENT FORM ---
        AnimatedContent(
            targetState = currentStep,
            label = "StepAnimation",
            modifier = Modifier.weight(1f)
        ) { step ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                when (step) {
                    1 -> Step1(
                        username = uiState.registerName,
                        email = uiState.registerEmail,
                        phone = uiState.registerPhone,
                        address = uiState.registerAddress,
                        password = uiState.registerPassword,
                        passwordConfirmation = uiState.registerConfirmation,
                        isTermsAccepted = uiState.isTermsAcceptedUser,

                        usernameError = uiState.registerNameError,
                        emailError = uiState.registerEmailError,
                        phoneError = uiState.registerPhoneError,
                        addressError = uiState.registerAddressError,
                        passwordError = uiState.registerPasswordError,
                        passwordConfirmationError = uiState.registerConfirmationError,

                        onNameChange = viewModel::onRegisterNameChanged,
                        onEmailChange = viewModel::onRegisterEmailChanged,
                        onPhoneChange = viewModel::onRegisterPhoneChanged,
                        onAddressChange = viewModel::onRegisterAddressChanged,
                        onPasswordChange = viewModel::onRegisterPasswordChanged,
                        onPasswordConfirmationChange = viewModel::onRegisterConfirmationChanged,
                        onTermsAcceptedChange = viewModel::onTermsAcceptedUserChanged,

                        onNext = { currentStep = 2 }
                    )
                    2 -> Step2(
                        description = uiState.description,
                        descriptionError = uiState.descriptionError,
                        onDescriptionChange = viewModel::onDescriptionChanged,
                        isTermsAccepted = uiState.isTermsAcceptedJob,
                        onTermsAcceptedChange = viewModel::onTermsAcceptedJobChanged,
                        onNext = { currentStep = 3 },
                    )
                    3 -> Step3(onFinish = { /* TODO: Enviar a API */ })
                }
            }
        }
    }
}