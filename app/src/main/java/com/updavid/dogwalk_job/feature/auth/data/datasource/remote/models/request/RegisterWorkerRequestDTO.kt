package com.updavid.liveoci.features.singinup.data.datasource.remote.models.request

data class RegisterWorkerRequestDTO(
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val password: String,
    val role: String,
    val job: JobDTO,                // Objeto anidado
    val services: List<ServiceDTO>  // Array de objetos
)

data class JobDTO(
    val description: String,
    val experience: String,
    val active: Boolean
)

data class ServiceDTO(
    val typeService: String,
    val nameService: String
)