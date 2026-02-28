package com.updavid.dogwalk_user.feature.auth.domain.entitie

data class User(
    val username: String,
    val email: String,
    val password: String,
    val adress: String
)
