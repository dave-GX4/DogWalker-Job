package com.updavid.dogwalk_user.feature.auth.domain.usecases

import com.updavid.dogwalk_user.feature.auth.domain.entitie.Response
import com.updavid.dogwalk_user.feature.auth.domain.entitie.WorkerRegistration
import com.updavid.dogwalk_user.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class PostSingUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    private val validStatuses = listOf(200, 201, 202, 203)

    suspend operator fun invoke(workerData: WorkerRegistration): Result<Response> {
        return try {
            val response = repository.postSingUp(workerData)

            if (response.status in validStatuses){
                Result.success(response)
            } else {
                Result.failure(Exception(response.message))
            }
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión. Por favor, revisa tu internet."))
        }
    }
}