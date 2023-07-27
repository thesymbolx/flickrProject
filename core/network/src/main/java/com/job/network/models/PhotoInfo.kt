package com.job.network.models

import com.squareup.moshi.Json

data class PhotoInfoResponse(val photo: PhotoInfo)

data class PhotoInfo(
    val id: String,
    val secret: String,
    val server: String,
    val owner: Owner,
    val title: Title,
    val dates: Dates,
    val urls: Urls
)

data class Owner (
    val nsid : String?,
    var username   : String?,
    var realname   : String?,
    var location   : String?,
    @Json(name = "iconserver") var iconServer : String?,
    @Json(name = "iconfarm"  ) var iconFarm   : Int?,
    @Json(name = "path_alias") var pathAlias  : String?
)

data class Urls(val url: List<PhotoUrl>)

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