package com.job.photos.ui

data class PhotoScreenState(
    val photos: List<Photo>
) {
    constructor() : this(emptyList())
}

data class Photo(
    val url: String
)