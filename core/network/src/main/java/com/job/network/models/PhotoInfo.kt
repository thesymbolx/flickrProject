package com.job.network.models

import com.squareup.moshi.Json

data class PhotoInfoResponse(val photo: PhotoInfo)

data class PhotoInfo(
    val id: String,
    val secret: String,
    val server: String,
    val owner: Owner,
    val title: Title,
    val dates: Dates
)

data class Owner (
    val nsid : String?,
    var username   : String?,
    var realname   : String?,
    var location   : String?,
    @Json(name = "iconserver") var iconServer : String?,
    @Json(name = "iconfarm"  ) var iconFarm   : Int?,
)

data class PhotoUrl(
    @Json(name = "_content") val content: String
)

data class Title(
    @Json(name = "_content") val content: String
)

data class Dates(
    val posted: Long,
    val taken: String,
)