package com.updavid.dogwalk_user.feature.auth.data.repositoryImpl

import android.util.Log
import com.updavid.dogwalk_user.feature.auth.data.datasource.remote.api.DogWalkApi
import com.updavid.dogwalk_user.feature.auth.data.datasource.remote.mapper.toDomain
import com.updavid.dogwalk_user.feature.auth.domain.entitie.Auth
import com.updavid.dogwalk_user.feature.auth.domain.entitie.WorkerRegistration
import com.updavid.dogwalk_user.feature.auth.domain.repository.AuthRepository
import com.updavid.liveoci.features.singinup.data.datasource.remote.models.request.JobDTO
import com.updavid.liveoci.features.singinup.data.datasource.remote.models.request.LoginRequestDTO
import com.updavid.liveoci.features.singinup.data.datasource.remote.models.request.RegisterWorkerRequestDTO
import com.updavid.liveoci.features.singinup.data.datasource.remote.models.request.ServiceDTO
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

    override suspend fun postSingUp(workerData: WorkerRegistration): Auth {
        // Construimos el JSON (DTO) a partir de la entidad
        val requestBody = RegisterWorkerRequestDTO(
            name = workerData.name,
            email = workerData.email,
            phone = workerData.phone,
            address = workerData.address,
            password = workerData.password,
            role = workerData.role,
            job = JobDTO(
                description = workerData.description,
                experience = workerData.experience,
                // Como dijiste: active puede ser falso si backgroundCheck es falso
                active = workerData.inService && workerData.backgroundCheck
            ),
            services = listOf(
                ServiceDTO(
                    typeService = workerData.serviceCategory,
                    nameService = workerData.specificService
                )
            )
        )

        // Llamada a Retrofit/Ktor
        val responseDto = api.postSingUp(requestBody)

        Log.d("API_RESPONSE", responseDto.toString())

        return responseDto.toDomain()
    }
}