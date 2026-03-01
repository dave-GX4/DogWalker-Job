package com.updavid.dogwalk_job.feature.auth.presentation.conponents.Registre

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.updavid.dogwalk_job.core.atoms.DropdownSelector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Step3(
    selectedType: String,
    selectedService: String,
    serviceTypesMap: Map<String, List<String>>,
    requiresCertificateList: List<String>,

    onServiceTypeChange: (String) -> Unit,
    onServiceChange: (String) -> Unit,
    onFinish: () -> Unit
) {
    val context = LocalContext.current
    // Calcula dinámicamente si el servicio elegido necesita certificado
    val requiresCertificate = requiresCertificateList.contains(selectedService)

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            "Servicios a Proporcionar",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Servicios a Proporcionar",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        DropdownSelector(
            value = selectedType,
            label = "Categoría del Servicio",
            // Convertimos las llaves del Map a una Lista
            options = serviceTypesMap.keys.toList(),
            onValueChange = onServiceTypeChange,
            modifier = Modifier.fillMaxWidth()
        )

        if (selectedType.isNotEmpty()) {
            val serviceOptions = serviceTypesMap[selectedType] ?: emptyList()

            DropdownSelector(
                value = selectedService,
                label = "Servicio Específico",
                options = serviceOptions,
                onValueChange = onServiceChange,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Upload de Certificado Condicional
        if (requiresCertificate) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Este servicio requiere Certificación", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                    .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)), RoundedCornerShape(12.dp))
                    .clickable {
                        Toast.makeText(context, "Abrir selector de archivos", Toast.LENGTH_SHORT).show()
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.CloudUpload, contentDescription = "Upload", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Subir Certificado (PDF o JPG)", fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onFinish,
            enabled = selectedService.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Finalizar Registro", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}