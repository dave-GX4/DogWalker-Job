package com.updavid.dogwalk_job.feature.auth.presentation.conponents.Registre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.updavid.dogwalk_job.core.atoms.TermsCheckbox
import com.updavid.dogwalk_job.core.atoms.TextFieldComponent

@Composable
fun Step1(
    username: String,
    email: String,
    phone: String,
    address: String,
    password: String,
    passwordConfirmation: String,
    isTermsAccepted: Boolean,

    usernameError: String?,
    emailError: String?,
    phoneError: String?,
    addressError: String?,
    passwordError: String?,
    passwordConfirmationError: String?,

    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onAddressChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirmationChange: (String) -> Unit,
    onTermsAcceptedChange: (Boolean) -> Unit,

    onNext: () -> Unit
) {
    val isFormValid = username.isNotBlank() && email.isNotBlank()
            && phone.isNotBlank() && address.isNotBlank()
            && password.isNotBlank() && usernameError == null
            && emailError == null && phoneError == null
            && addressError == null && passwordError == null

    val yellowTermsText = buildAnnotatedString {
        withStyle(SpanStyle(color = Color(0xFF4A5568))) {
            append("I agree to the ")
        }
        withStyle(SpanStyle(color = Color.Yellow, fontWeight = FontWeight.Bold)) {
            append("Terms & Conditions")
        }
        withStyle(SpanStyle(color = Color(0xFF4A5568))) {
            append(" and ")
        }
        withStyle(SpanStyle(color = Color.Yellow, fontWeight = FontWeight.Bold)) {
            append("Privacy Policy")
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            "Cuéntanos más sobre ti",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Esta información servira para tu configuración de perfil",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )

        TextFieldComponent(
            value = username,
            onValueChange = onNameChange,
            label = "Nombre Completo",
            errorMessage = usernameError
        )

        TextFieldComponent(
            value = email,
            onValueChange = onEmailChange,
            label = "Correo Electrónico",
            errorMessage = emailError,
            keyboardType = KeyboardType.Email
        )

        TextFieldComponent(
            value = phone,
            onValueChange = onPhoneChange,
            label = "Numero de teléfono",
            errorMessage = phoneError,
            keyboardType = KeyboardType.Phone
        )

        TextFieldComponent(
            value = address,
            onValueChange = onAddressChange,
            label = "Direccion",
            errorMessage = addressError
        )

        TextFieldComponent(
            value = password,
            onValueChange = onPasswordChange,
            label = "Contraseña",
            errorMessage = passwordError,
            isPassword = true
        )

        TextFieldComponent(
            value = passwordConfirmation,
            onValueChange = onPasswordConfirmationChange,
            label = "Confirmar Contraseña",
            errorMessage = passwordConfirmationError,
            isPassword = true
        )

        TermsCheckbox(
            checked = isTermsAccepted,
            onCheckedChange = onTermsAcceptedChange,
            text = yellowTermsText,
            primaryColor = Color.Yellow
        )

        Button(
            onClick = onNext,
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Siguiente", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}