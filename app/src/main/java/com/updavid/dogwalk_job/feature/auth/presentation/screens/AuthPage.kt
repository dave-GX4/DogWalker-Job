package com.updavid.dogwalk_job.feature.auth.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.updavid.dogwalk_job.feature.auth.presentation.conponents.Auth.FieldTextsWrapper
import com.updavid.dogwalk_job.feature.auth.presentation.conponents.Auth.PresentationSection
import com.updavid.dogwalk_job.feature.auth.presentation.conponents.Auth.RegisterSection
import com.updavid.dogwalk_user.feature.auth.presentation.viewmodel.AuthViewModel

@Composable
fun AuthPage(
    viewModel: AuthViewModel,
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: () -> Unit,
){
    val localContext = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.events.collect { event ->
            when (event) {
                is UiEvents.NavigateToMap -> {
                    onLoginSuccess()
                }
                is UiEvents.ShowError -> {
                    Toast.makeText(localContext, "⚠️ ${event.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                PresentationSection()

                FieldTextsWrapper(
                    email = uiState.loginEmail,
                    password = uiState.loginPassword,
                    emailError = uiState.loginEmailError,
                    passwordError = uiState.loginPasswordError,
                    onEmailChange = viewModel::onLoginEmailChanged,
                    onPasswordChange = viewModel::onLoginPasswordChanged,
                    onLoginClick = viewModel::onAuthentication,
                )

                RegisterSection(onNavigateToRegister)

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
                    .zIndex(10f)
                    .pointerInput(Unit) {},
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}