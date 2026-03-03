package com.updavid.dogwalk_user.feature.auth.data.datasource.remote.mapper

import com.updavid.dogwalk_user.feature.auth.data.datasource.remote.models.response.ResponseDTO
import com.updavid.dogwalk_user.feature.auth.domain.entitie.Response

fun ResponseDTO.toDomain(): Response {
    return Response(
        status = this.status,
        message = this.message,
    )
}