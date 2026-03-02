package com.updavid.dogwalk_job.feature.auth.presentation.conponents.Registre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.updavid.dogwalk_job.core.atoms.DropdownSelector
import com.updavid.dogwalk_job.core.atoms.SwitchCard
import com.updavid.dogwalk_job.core.atoms.TermsCheckbox
import com.updavid.dogwalk_job.core.atoms.TextFieldComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Step2(
    description: String,
    experience: String,
    experienceOptions: List<String>,
    isTermsAccepted: Boolean,
    inService: Boolean,
    backgroundCheck: Boolean,

    descriptionError: String?,

    onDescriptionChange : (String) -> Unit,
    onExperienceChange: (String) -> Unit,
    onTermsAcceptedChange: (Boolean) -> Unit,
    onInServiceChange: (Boolean) -> Unit,
    onBackgroundCheckChange: (Boolean) -> Unit,

    onNext: () -> Unit
) {
    val isFormValid = description.isNotEmpty() && descriptionError == null && isTermsAccepted && experience.isNotEmpty()

    val greenTermsText = buildAnnotatedString {
        withStyle(SpanStyle(color = Color.Black)) {
            append("I agree to the ")
        }
        // Aquí agregamos textDecoration = TextDecoration.Underline
        withStyle(SpanStyle(color = Color.Green, fontWeight = FontWeight.SemiBold, textDecoration = TextDecoration.Underline)) {
            append("Service Terms")
        }
        withStyle(SpanStyle(color = Color.Black)) {
            append(" and understand that a background check is required for final approval.")
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            "Credencial Profecional",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Verificamos a todos los acompañantes para mantener altos estándares de seguridad y confianza para nuestra comunidad.",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
        )

        TextFieldComponent(
            value = description,
            onValueChange = onDescriptionChange,
            label = "Descripción sobre ti",
            errorMessage = descriptionError,
            modifier = Modifier.fillMaxWidth().height(120.dp),
            max = 4
        )

        DropdownSelector(
            value = experience,
            label = "Nivel de Experiencia",
            options = experienceOptions,
            onValueChange = onExperienceChange,
            modifier = Modifier.fillMaxWidth()
        )

        SwitchCard(
            title = "En Servicio",
            subtitle = "Disponible para trabajar",
            checked = inService,
            onCheckedChange = onInServiceChange,
            activeColor = MaterialTheme.colorScheme.primary
        )

        SwitchCard(
            title = "Verificación de Antecedentes",
            subtitle = "Autorizar verificación profesional",
            checked = backgroundCheck,
            onCheckedChange = onBackgroundCheckChange,
            activeColor = MaterialTheme.colorScheme.primary
        )

        TermsCheckbox(
            checked = isTermsAccepted,
            onCheckedChange = onTermsAcceptedChange,
            text = greenTermsText,
            primaryColor = Color.Green
        )

        Button(
            onClick = onNext,
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Crear Cuenta de Trabajo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}