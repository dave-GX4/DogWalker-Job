package com.updavid.liveoci.features.singinup.data.datasource.remote.models.request

data class RegisterRequestDTO(
    val username: String,
    val email: String,
    val password: String,
    val adress: String
)
