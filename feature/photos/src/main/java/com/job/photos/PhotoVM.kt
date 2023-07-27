package com.job.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PhotoVM @Inject constructor(
    private val photoRepo: PhotoRepo
) : ViewModel() {
    val photos: Flow<PagingData<com.job.network.models.Photo>> = Pager(PagingConfig(pageSize = 250)) {
        PhotoDataSource(photoRepo)
    }.flow.cachedIn(viewModelScope)
}