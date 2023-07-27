package com.job.network.endpoints

import com.job.network.models.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoEndpoints {
    @GET(".")
    suspend fun getRecentPhotos(
        @Query("method") method: String = "flickr.photos.getRecent",
        @Query("extras") extras: String = "owner_name, tags, icon_server",
        @Query("safe_search") safeSearch: Int = 1,
        @Query("page") pageNo: Int,
        @Query("per_page") perPage: Int
    ): Response<PhotosResponse>

    @GET(".")
    suspend fun getPhotos(
        @Query("method") method: String = "flickr.photos.search",
        @Query("extras") extras: String = "owner_name, tags, icon_server",
        @Query("safe_search") safeSearch: Int = 1,
        @Query("page") pageNo: Int,
        @Query("per_page") perPage: Int,
        @Query("text") text: String,
    ): Response<PhotosResponse>
}