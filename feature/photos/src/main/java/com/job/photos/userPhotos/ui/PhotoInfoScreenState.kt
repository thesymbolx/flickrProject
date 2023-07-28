package com.job.photos.userPhotos.ui

import com.job.photos.utils.ScreenState

data class PhotoInfoScreenState(
    val screenState: ScreenState,
    val photoUrl: String,
    val title: String?,
    val realName: String?,
    val taken: String?,
    val postedOn: String?,
    val userPhoto: List<UserPhoto>
) {
    constructor() : this(ScreenState.Success, "", null, null, null, null, emptyList())
}