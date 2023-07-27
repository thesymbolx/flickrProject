package com.job.photos.utils

fun getPhotoUrl(
    server: String,
    photoId: String,
    secret: String
) = "https://live.staticflickr.com/$server/${photoId}_${secret}.jpg"