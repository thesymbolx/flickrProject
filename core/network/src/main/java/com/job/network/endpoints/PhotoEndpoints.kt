package com.job.network.endpoints

import com.job.network.models.PopularPhotosResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhotoEndpoints {

    @GET
    suspend fun getPopularPhotos(
    ) : Response<PopularPhotosResponse>

}