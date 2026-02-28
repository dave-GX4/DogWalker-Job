package com.updavid.dogwalk_job.core.navigation.di

import com.updavid.dogwalk_job.core.navigation.FeatureNavGraph
import com.updavid.dogwalk_job.feature.auth.navigation.AuthNavGraph
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {
    @Binds
    @IntoSet
    abstract fun bindAuthNavGraph(
        impl: AuthNavGraph
    ): FeatureNavGraph
}