package com.job.network.di

import com.job.network.endpoints.PeopleEndpoints
import com.job.network.endpoints.PhotoEndpoints
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
    ) : PhotoEndpoints = retrofit.create(PhotoEndpoints::class.java)

    @Provides
    fun providePeopleServices(
        retrofit: Retrofit
    ) : PeopleEndpoints = retrofit.create(PeopleEndpoints::class.java)
}