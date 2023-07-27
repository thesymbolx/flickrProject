package com.job.photos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.job.network.Resource
import com.job.photos.ui.Photo
import com.job.photos.ui.PhotoScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoVM @Inject constructor(
    private val photoRepo: PhotoRepo
) : ViewModel() {
    private val photoUrl = "https://live.staticflickr.com/"
    var photoScreenState by mutableStateOf(PhotoScreenState())

    init {
        getRecentPhotos()
    }

    private fun getRecentPhotos() = viewModelScope.launch {
        photoScreenState = when(val result = photoRepo.getPopularPhotos()) {
            is Resource.Empty -> photoScreenState.copy(photos = emptyList())
            is Resource.Error -> photoScreenState.copy(photos = emptyList())
            is Resource.Success -> photoScreenState.copy(photos = result.data.photos.photo.map {
                Photo("$photoUrl${it.server}/${it.id}_${it.secret}.jpg")
            })
        }
    }
}