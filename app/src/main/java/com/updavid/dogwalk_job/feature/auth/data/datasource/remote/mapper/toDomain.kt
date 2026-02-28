package com.updavid.dogwalk_user.feature.auth.data.datasource.remote.mapper

import com.updavid.dogwalk_user.feature.auth.data.datasource.remote.models.response.AuthDTO
import com.updavid.dogwalk_user.feature.auth.domain.entitie.Auth

fun AuthDTO.toDomain(): Auth {
    return Auth(
        email = this.email,
        message = this.message,
    )
}