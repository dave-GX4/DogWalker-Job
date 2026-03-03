package com.updavid.dogwalk_user.feature.auth.data.datasource.remote.api

import com.updavid.dogwalk_user.feature.auth.data.datasource.remote.models.response.ResponseDTO
import com.updavid.liveoci.features.singinup.data.datasource.remote.models.request.LoginRequestDTO
import com.updavid.liveoci.features.singinup.data.datasource.remote.models.request.RegisterWorkerRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface DogWalkApi {
    @POST("auth/singIn")
    suspend fun postSingIn(
        @Body request: LoginRequestDTO
    ): ResponseDTO

    @POST("auth/register")
    suspend fun postSingUp(
        @Body request: RegisterWorkerRequestDTO
    ): ResponseDTO
}