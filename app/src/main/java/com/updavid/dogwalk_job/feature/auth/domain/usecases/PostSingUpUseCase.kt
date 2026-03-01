package com.updavid.dogwalk_user.feature.auth.domain.usecases

import com.updavid.dogwalk_user.feature.auth.domain.entitie.Auth
import com.updavid.dogwalk_user.feature.auth.domain.entitie.WorkerRegistration
import com.updavid.dogwalk_user.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class PostSingUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(workerData: WorkerRegistration): Result<Auth> {
        return try {
            val authResponse = repository.postSingUp(workerData)
            Result.success(authResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}