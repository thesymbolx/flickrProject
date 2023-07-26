package com.job.network.di

import com.job.network.FLICKR_ENDPOINT_URL
import com.job.network.FLICKR_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(ViewModelComponent::class)
class ApiModule {
    @Provides
    fun provideMoshiBuilder(): Moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun okHttp(
        okHttpInterceptor: Interceptor
    ) : OkHttpClient =
        OkHttpClient.Builder().addInterceptor(okHttpInterceptor).build()

    @Provides
    fun okHttpInterceptor() : Interceptor =
        Interceptor {
            val original: Request = it.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", FLICKR_KEY)
                .addQueryParameter("format", "json")
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            it.proceed(request)
        }

    @Provides
    fun retrofit(
        okHttp: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(FLICKR_ENDPOINT_URL)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
}