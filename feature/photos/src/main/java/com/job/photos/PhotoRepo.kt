package com.job.photos

import com.job.network.endpoints.PhotoEndpoints
import com.job.network.getNetworkResource
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoEndpoints: PhotoEndpoints
) {

    suspend fun getPopularPhotos() =
        photoEndpoints.getRecentPhotos().getNetworkResource()
}