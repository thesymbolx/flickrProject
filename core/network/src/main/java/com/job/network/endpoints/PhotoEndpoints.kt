package com.job.network.endpoints

import com.job.network.models.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoEndpoints {
    @GET(".")
    suspend fun getRecentPhotos(
        @Query("method") method: String = "flickr.photos.getRecent",
        @Query("extras") extras: String = "url_sq, url_t, url_s, url_q, url_m, url_n, url_z, url_c, url_l, url_o"
    ): Response<PhotosResponse>
}