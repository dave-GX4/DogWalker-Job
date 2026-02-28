package com.updavid.dogwalk_job.feature.auth.presentation.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
}