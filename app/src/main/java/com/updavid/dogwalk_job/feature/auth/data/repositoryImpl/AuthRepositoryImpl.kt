package com.updavid.dogwalk_user.feature.auth.data.repositoryImpl

import android.util.Log
import com.updavid.dogwalk_user.feature.auth.data.datasource.remote.api.DogWalkApi
import com.updavid.dogwalk_user.feature.auth.data.datasource.remote.mapper.toDomain
import com.updavid.dogwalk_user.feature.auth.domain.entitie.Auth
import com.updavid.dogwalk_user.feature.auth.domain.entitie.User
import com.updavid.dogwalk_user.feature.auth.domain.repository.AuthRepository
import com.updavid.liveoci.features.singinup.data.datasource.remote.models.request.LoginRequestDTO
import com.updavid.liveoci.features.singinup.data.datasource.remote.models.request.RegisterRequestDTO
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: DogWalkApi
): AuthRepository{
    override suspend fun postSingIn(email: String, password: String): Auth {
        val requestBody = LoginRequestDTO(
            email = email,
            password = password
        )

        val responseDto = api.postSingIn(requestBody)

        Log.d("API_RESPONSE", responseDto.toString())

        return responseDto.toDomain()
    }

    override suspend fun postSingUp(user: User): Auth {
        val requestBody = RegisterRequestDTO(
            username = user.username,
            email = user.email,
            password = user.password,
            adress = user.adress
        )

        val responseDto = api.postSingUp(requestBody)

        Log.d("API_RESPONSE", responseDto.toString())

        return responseDto.toDomain()
    }
}