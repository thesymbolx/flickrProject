package com.job.devices.photoSearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.job.network.models.Device
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DevicesVM @Inject constructor(
    private val deviceDataSource: DeviceDataSource
) : ViewModel() {
    val devices: Flow<PagingData<Device>> = Pager(PagingConfig(pageSize = 50)) {
        deviceDataSource
    }.flow.cachedIn(viewModelScope)
}