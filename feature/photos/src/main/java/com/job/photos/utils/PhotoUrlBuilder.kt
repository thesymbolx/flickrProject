package com.job.photos.utils

fun getPhotoUrl(
    server: String,
    photoId: String,
    secret: String
) = "https://live.staticflickr.com/$server/${photoId}_${secret}.jpg"

fun getBuddyUrl(iconFarm: String, iconServer: Int, owner: String) =
    if (iconServer > 0)
        "https://farm$iconFarm.staticflickr.com/$iconServer/buddyicons/$owner.jpg"
    else
        "https://www.flickr.com/images/buddyicon.gif"