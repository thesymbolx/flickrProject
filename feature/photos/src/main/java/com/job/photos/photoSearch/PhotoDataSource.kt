package com.job.photos.photoSearch

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.job.network.Resource
import com.job.network.models.Photo
import javax.inject.Inject

class PhotoDataSource @Inject constructor(
    private val photoRepo: PhotoRepo
) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val nextPage = params.key ?: 1

        return when (val response = photoRepo.getPhotos(
            pageNo = nextPage,
            perPage = params.loadSize,
            text = "flowers"
        )) {
            is Resource.Empty -> LoadResult.Error(Exception(""))
            is Resource.Error -> LoadResult.Error(Exception(""))
            is Resource.Success -> {
                LoadResult.Page(
                    data = response.data.photos.photo,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (nextPage < response.data.photos.pages) nextPage + 1 else null
                )
            }
        }
    }
}