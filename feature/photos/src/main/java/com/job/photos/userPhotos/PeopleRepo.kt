package com.job.photos.userPhotos

import com.job.network.endpoints.PeopleEndpoints
import com.job.network.getNetworkResource
import javax.inject.Inject

class PeopleRepo @Inject constructor(
    private val peopleEndpoints: PeopleEndpoints
) {
    suspend fun getUsersPhotos(userId: String) =
        peopleEndpoints.getPhotoOfUser(userId = userId).getNetworkResource()
}