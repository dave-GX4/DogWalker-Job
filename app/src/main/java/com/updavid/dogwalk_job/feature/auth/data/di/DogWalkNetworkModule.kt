package com.updavid.dogwalk_user.feature.auth.data.di

import com.updavid.dogwalk_user.core.di.DogWalkRetrofit
import com.updavid.dogwalk_user.feature.auth.data.datasource.remote.api.DogWalkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogWalkNetworkModule {
    @Provides
    @Singleton
    fun provideDogWalkApi(@DogWalkRetrofit retrofit: Retrofit): DogWalkApi{
        return retrofit.create(DogWalkApi::class.java)
    }
}