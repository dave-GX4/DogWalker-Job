package com.updavid.dogwalk_user.feature.auth.domain.usecases

import com.updavid.dogwalk_user.feature.auth.domain.entitie.Auth
import com.updavid.dogwalk_user.feature.auth.domain.entitie.User
import com.updavid.dogwalk_user.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class PostSingUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: User): Result<Auth> {
        return try {
            if (user.password.length < 6) {
                return Result.failure(Exception("La contraseña debe tener al menos 6 caracteres"))
            }

            val authResponse = repository.postSingUp(user)

            Result.success(authResponse)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}