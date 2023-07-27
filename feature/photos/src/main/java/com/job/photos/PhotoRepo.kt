package com.job.photos

import com.job.network.endpoints.PhotoEndpoints
import com.job.network.getNetworkResource
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoEndpoints: PhotoEndpoints
) {

    suspend fun getPopularPhotos(
        pageNo: Int,
        perPage: Int
    ) =
        photoEndpoints.getRecentPhotos(
            pageNo = pageNo,
            perPage = perPage
        ).getNetworkResource()

    suspend fun getPhotos(
        pageNo: Int,
        perPage: Int,
        text: String,
    ) =
        photoEndpoints.getPhotos(
            pageNo = pageNo,
            perPage = perPage,
            text = text
        ).getNetworkResource()
}