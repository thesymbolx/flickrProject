package com.job.network.models

data class PopularPhotosResponse(
    val photos: List<Photo>
)

data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val title: String,
)