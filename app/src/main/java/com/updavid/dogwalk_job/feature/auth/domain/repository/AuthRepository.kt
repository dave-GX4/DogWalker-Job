package com.updavid.dogwalk_user.feature.auth.domain.repository

import com.updavid.dogwalk_user.feature.auth.domain.entitie.Response
import com.updavid.dogwalk_user.feature.auth.domain.entitie.WorkerRegistration

interface AuthRepository {
    suspend fun postSingIn(email: String, password: String): Response

    suspend fun postSingUp(workerRegistration: WorkerRegistration): Response
}