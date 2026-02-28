package com.updavid.dogwalk_job.feature.auth.presentation.conponents.Auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.updavid.dogwalk_job.core.atoms.TextFieldComponent

@Composable
fun FieldTextsWrapper(
    email: String,
    password: String,
    emailError: String?,
    passwordError: String?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextFieldComponent(
            value = email,
            onValueChange = onEmailChange,
            label = "Correo Electrónico",
            errorMessage = emailError,
            keyboardType = KeyboardType.Email
        )

        TextFieldComponent(
            value = password,
            onValueChange = onPasswordChange,
            label = "Contraseña",
            errorMessage = passwordError,
            isPassword = true
        )

        Text(
            text = "¿Olvidaste tu contraseña?",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /* Lógica de recuperar contraseña */ }
                .padding(vertical = 4.dp)
        )

        OutlinedButton(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth().height(60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Text(text = "Iniciar Sesión", fontWeight = FontWeight.Bold)
        }
    }
}