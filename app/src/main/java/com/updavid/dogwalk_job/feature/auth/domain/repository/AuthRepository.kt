package com.updavid.dogwalk_user.feature.auth.domain.repository

import com.updavid.dogwalk_user.feature.auth.domain.entitie.Auth
import com.updavid.dogwalk_user.feature.auth.domain.entitie.User

interface AuthRepository {
    suspend fun postSingIn(email: String, password: String): Auth

    suspend fun postSingUp(user: User): Auth
}