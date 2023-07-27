package com.job.network.endpoints

import com.job.network.models.PhotoInfoResponse
import com.job.network.models.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleEndpoints {
    @GET(".")
    suspend fun getPhotoOfUser(
        @Query("method") method: String = "flickr.people.getPhotos",
        @Query("extras") extras: String = "owner_name, tags, icon_server",
        @Query("user_id") userId: String
    ): Response<PhotosResponse>

}