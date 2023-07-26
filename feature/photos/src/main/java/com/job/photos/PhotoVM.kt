package com.job.photos

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoVM @Inject constructor(
    val photoRepo: PhotoRepo
) {



}