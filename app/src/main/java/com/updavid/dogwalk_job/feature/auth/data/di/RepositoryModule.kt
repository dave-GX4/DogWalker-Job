package com.updavid.dogwalk_user.feature.auth.data.di

import com.updavid.dogwalk_user.feature.auth.data.repositoryImpl.AuthRepositoryImpl
import com.updavid.dogwalk_user.feature.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(
        postsRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}