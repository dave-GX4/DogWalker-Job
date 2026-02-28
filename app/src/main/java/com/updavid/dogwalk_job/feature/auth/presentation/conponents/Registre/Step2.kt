package com.updavid.dogwalk_job.feature.auth.presentation.conponents.Registre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.updavid.dogwalk_job.core.atoms.SwitchCard
import com.updavid.dogwalk_job.core.atoms.TermsCheckbox
import com.updavid.dogwalk_job.core.atoms.TextFieldComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Step2(
    description: String,
    isTermsAccepted: Boolean,

    descriptionError: String?,

    onDescriptionChange : (String) -> Unit,
    onTermsAcceptedChange: (Boolean) -> Unit,

    onNext: () -> Unit
) {
    val PrimaryGreen = Color(0xFF13EC5B)
    var experience by remember { mutableStateOf("") }
    var experienceExpanded by remember { mutableStateOf(false) }
    val experienceOptions = listOf("Menos de 1 año", "1-3 años", "3-5 años", "Más de 5 años")

    var inService by remember { mutableStateOf(true) }
    var backgroundCheck by remember { mutableStateOf(false) }
    var termsAccepted by remember { mutableStateOf(false) }

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

        // Selector de Experiencia
        ExposedDropdownMenuBox(
            expanded = experienceExpanded,
            onExpandedChange = { experienceExpanded = !experienceExpanded }
        ) {
            OutlinedTextField(
                value = experience, onValueChange = {},
                readOnly = true,
                label = { Text("Nivel de Experiencia") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = experienceExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = experienceExpanded, onDismissRequest = { experienceExpanded = false }) {
                experienceOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = { experience = option; experienceExpanded = false }
                    )
                }
            }
        }

        SwitchCard(
            title = "En Servicio",
            subtitle = "Disponible para trabajar",
            checked = inService,
            onCheckedChange = { inService = it },
            activeColor = PrimaryGreen
        )

        SwitchCard(
            title = "Verificación de Antecedentes",
            subtitle = "Autorizar verificación profesional",
            checked = backgroundCheck,
            onCheckedChange = { backgroundCheck = it },
            activeColor = PrimaryGreen
        )

        TermsCheckbox(
            checked = isTermsAccepted,
            onCheckedChange = onTermsAcceptedChange,
            text = greenTermsText,
            primaryColor = Color.Green
        )

        Button(
            onClick = onNext,
            enabled = description.isNotBlank() && experience.isNotBlank() && termsAccepted,
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Crear Cuenta de Trabajo", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}