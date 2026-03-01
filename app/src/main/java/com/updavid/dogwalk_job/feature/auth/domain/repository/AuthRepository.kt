package com.updavid.dogwalk_user.feature.auth.domain.repository

import com.updavid.dogwalk_user.feature.auth.domain.entitie.Auth
import com.updavid.dogwalk_user.feature.auth.domain.entitie.WorkerRegistration

interface AuthRepository {
    suspend fun postSingIn(email: String, password: String): Auth

    suspend fun postSingUp(workerRegistration: WorkerRegistration): Auth
}