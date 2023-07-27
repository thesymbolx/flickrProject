package com.job.photos.ui

data class PhotoScreenState(
    val photos: List<Photo>
) {
    constructor() : this(emptyList())
}

data class Photo(
    val key: String,
    val url: String,
    val ownerName: String,
    val tags: String,
    val userIconUrl: String
)