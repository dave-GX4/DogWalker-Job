package com.updavid.dogwalk_user.feature.auth.domain.usecases

import com.updavid.dogwalk_user.feature.auth.domain.entitie.Auth
import com.updavid.dogwalk_user.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class PostSingInUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke(email: String, password: String): Result<Auth> {
        return try {
            if (email.isBlank() || password.isBlank()) {
                return Result.failure(Exception("El correo y la contraseña no pueden estar vacíos"))
            }

            val authResponse = repository.postSingIn(email, password)

            Result.success(authResponse)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}