package com.job.photos.photoSearch

import com.job.network.endpoints.PhotoEndpoints
import com.job.network.getNetworkResource
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoEndpoints: PhotoEndpoints
) {

    suspend fun getPhotoInfo(
        photoId: String
    ) =
        photoEndpoints.getPhotoInfo(
            photoId = photoId
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