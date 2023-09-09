package com.job.network.endpoints

import com.job.network.models.DevicesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DevicesEndpoints {
    @GET("devices")
    suspend fun getDevices(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): Response<DevicesResponse>
}