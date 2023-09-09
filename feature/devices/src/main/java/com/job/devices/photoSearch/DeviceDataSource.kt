package com.job.devices.photoSearch

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.job.network.Resource
import com.job.network.models.Device
import javax.inject.Inject

class DeviceDataSource @Inject constructor(
    private val deviceRepo: DeviceRepo
) : PagingSource<Int, Device>() {
    override fun getRefreshKey(state: PagingState<Int, Device>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Device> {
        val nextPage = params.key ?: 1

        return when (val response = deviceRepo.getDevices(
            page = nextPage,
            pageSize = 50,
        )) {
            is Resource.Empty -> LoadResult.Error(Exception(""))
            is Resource.Error -> LoadResult.Error(Exception(""))
            is Resource.Success -> {
                LoadResult.Page(
                    data = response.data.devices,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (nextPage < response.data.totalPages) nextPage + 1 else null
                )
            }
        }
    }
}