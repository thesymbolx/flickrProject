package com.job.network.di

import com.job.network.endpoints.DevicesEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {
    @Provides
    fun providePhotoServices(
        retrofit: Retrofit
    ) : DevicesEndpoints = retrofit.create(DevicesEndpoints::class.java)
}