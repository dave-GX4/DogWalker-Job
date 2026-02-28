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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Step3(onFinish: () -> Unit) {
    val context = LocalContext.current
    val PrimaryGreen = Color(0xFF13EC5B)
    // Datos mapeados de tu archivo TypeScript
    val serviceTypesMap = mapOf(
        "Movilidad & Ejercicio" to listOf("Paseo de perros", "Running con perros", "Transporte de mascotas", "Aventura outdoor"),
        "Cuidado en Hogar" to listOf("Cuidado en casa del dueño", "Cuidado en casa del cuidador", "Visitas a domicilio", "Compañía para mascota anciana"),
        "Higiene & Estética" to listOf("Baño básico a domicilio", "Grooming completo", "Spa y bienestar", "Limpieza dental"),
        "Entrenamiento & Comportamiento" to listOf("Entrenamiento básico", "Entrenamiento avanzado", "Modificación de conducta", "Socialización guiada", "Entrenamiento para cachorros"),
        "Salud & Bienestar" to listOf("Visita al veterinario", "Fisioterapia canina", "Alimentación especializada"),
        "Extras" to listOf("Fotografía de mascotas", "Cumpleaños/eventos para mascotas", "Entrega de alimentos/suministros")
    )

    val requiresCertificateList = listOf(
        "Transporte de mascotas", "Cuidado en casa del cuidador", "Compañía para mascota anciana",
        "Grooming completo", "Spa y bienestar", "Limpieza dental", "Entrenamiento avanzado",
        "Modificación de conducta", "Socialización guiada", "Visita al veterinario",
        "Fisioterapia canina", "Alimentación especializada", "Fotografía de mascotas",
        "Cumpleaños/eventos para mascotas", "Entrega de alimentos/suministros"
    )

    var typeExpanded by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf("") }

    var serviceExpanded by remember { mutableStateOf(false) }
    var selectedService by remember { mutableStateOf("") }

    // Calcula dinámicamente si el servicio elegido necesita certificado
    val requiresCertificate = requiresCertificateList.contains(selectedService)

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("Servicios a Proporcionar", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        // 1. Selector de Tipo de Servicio
        ExposedDropdownMenuBox(expanded = typeExpanded, onExpandedChange = { typeExpanded = !typeExpanded }) {
            OutlinedTextField(
                value = selectedType, onValueChange = {}, readOnly = true,
                label = { Text("Categoría del Servicio") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = typeExpanded) },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = typeExpanded, onDismissRequest = { typeExpanded = false }) {
                serviceTypesMap.keys.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type) },
                        onClick = {
                            selectedType = type
                            selectedService = "" // Limpiar el servicio si cambia la categoría
                            typeExpanded = false
                        }
                    )
                }
            }
        }

        // 2. Selector del Servicio Específico (Se habilita solo si hay un tipo seleccionado)
        if (selectedType.isNotEmpty()) {
            ExposedDropdownMenuBox(expanded = serviceExpanded, onExpandedChange = { serviceExpanded = !serviceExpanded }) {
                OutlinedTextField(
                    value = selectedService, onValueChange = {}, readOnly = true,
                    label = { Text("Servicio Específico") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = serviceExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = serviceExpanded, onDismissRequest = { serviceExpanded = false }) {
                    serviceTypesMap[selectedType]?.forEach { service ->
                        DropdownMenuItem(
                            text = { Text(service) },
                            onClick = {
                                selectedService = service
                                serviceExpanded = false
                            }
                        )
                    }
                }
            }
        }

        // 3. Upload de Certificado Condicional
        if (requiresCertificate) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Este servicio requiere Certificación", fontWeight = FontWeight.Bold, color = PrimaryGreen)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(PrimaryGreen.copy(alpha = 0.1f), RoundedCornerShape(12.dp))
                    .border(BorderStroke(2.dp, PrimaryGreen.copy(alpha = 0.5f)), RoundedCornerShape(12.dp))
                    .clickable {
                        Toast.makeText(context, "Abrir selector de archivos", Toast.LENGTH_SHORT).show()
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.CloudUpload, contentDescription = "Upload", tint = PrimaryGreen, modifier = Modifier.size(48.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Subir Certificado (PDF o JPG)", fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onFinish,
            enabled = selectedService.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Color.Black),
            modifier = Modifier.fillMaxWidth().height(56.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Finalizar Registro", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}