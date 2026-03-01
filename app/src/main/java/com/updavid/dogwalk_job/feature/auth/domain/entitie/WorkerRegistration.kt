package com.updavid.dogwalk_user.feature.auth.domain.entitie

data class WorkerRegistration(
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val password: String,
    val role: String = "WORKER", // Le avisamos al backend qué rol es

    // Datos de la tabla JOB
    val description: String,
    val experience: String,
    val inService: Boolean,
    val backgroundCheck: Boolean,

    // Datos de la tabla SERVICES (Por ahora solo enviamos 1, el que seleccionó en Step 3)
    val serviceCategory: String,
    val specificService: String
)
