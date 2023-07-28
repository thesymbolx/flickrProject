package com.job.photos.userPhotos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.job.network.Resource
import com.job.network.models.PhotoInfo
import com.job.photos.photoSearch.PhotoRepo
import com.job.photos.userPhotos.ui.PhotoInfoScreenState
import com.job.photos.userPhotos.ui.UserPhoto
import com.job.photos.utils.ScreenState
import com.job.photos.utils.getPhotoUrl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class UserPhotoVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val photoRepo: PhotoRepo,
    private val peopleRepo: PeopleRepo
) : ViewModel() {
    private val photoId: String = checkNotNull(savedStateHandle["photoId"])
    var userPhotoScreenState by mutableStateOf(PhotoInfoScreenState())

    init {
        getPhotoInfo()
    }

    private fun getPhotoInfo() = viewModelScope.launch {
        when(val result = photoRepo.getPhotoInfo(photoId)) {
            is Resource.Empty -> userPhotoScreenState.copy(
                screenState = ScreenState.Error
            )
            is Resource.Error -> userPhotoScreenState.copy(
                screenState = ScreenState.Error
            )
            is Resource.Success -> showPhotoDetails(result.data.photo)
        }
    }

    private fun showPhotoDetails(photoInfo: PhotoInfo) {
        with(photoInfo) {
            userPhotoScreenState = userPhotoScreenState.copy(
                photoUrl = getPhotoUrl(server, id, secret),
                title = title.content,
                realName = owner.realname,
                taken = dates.taken,
                postedOn = formattedPostTime(dates.posted)
            )

            owner.nsid?.let {
                getUserPhotos(it)
            }
        }
    }

    private fun getUserPhotos(userId: String) = viewModelScope.launch {
        userPhotoScreenState = when(val result = peopleRepo.getUsersPhotos(userId)) {
            is Resource.Empty -> userPhotoScreenState.copy(
                screenState = ScreenState.Error
            )
            is Resource.Error -> userPhotoScreenState.copy(
                screenState = ScreenState.Error
            )
            is Resource.Success -> userPhotoScreenState.copy(
                screenState = ScreenState.Success,
                userPhoto = result.data.photos.photo.map {
                    UserPhoto(getPhotoUrl(server = it.server, photoId = it.id, secret = it.secret))
                }
            )
        }
    }

    private fun formattedPostTime(datePosted: Long) : String {
        val instant = Instant.ofEpochSecond(datePosted)
        val date = ZonedDateTime.ofInstant(instant, ZoneOffset.systemDefault())
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z")
        return date.format(formatter)
    }
}