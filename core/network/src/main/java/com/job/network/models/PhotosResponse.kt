package com.job.network.models

import com.squareup.moshi.Json

data class PhotosResponse(
    val photos: Photos
)

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: List<Photo>
)

data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val title: String,
    val server: String,
    @Json(name = "iconserver")
    val iconServer: Int,
    @Json(name = "iconfarm")
    val iconFarm: String,
    @Json(name = "ownername")
    val ownerName: String,
    val tags: String
)