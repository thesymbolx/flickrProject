package com.job.photos

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.job.common.MainDispatcherRule
import com.job.network.Resource
import com.job.network.models.Dates
import com.job.network.models.Owner
import com.job.network.models.Photo
import com.job.network.models.PhotoInfo
import com.job.network.models.PhotoInfoResponse
import com.job.network.models.Photos
import com.job.network.models.PhotosResponse
import com.job.network.models.Title
import com.job.photos.photoSearch.PhotoRepo
import com.job.photos.userPhotos.PeopleRepo
import com.job.photos.userPhotos.UserPhotoVM
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.kotlin.mock
import org.junit.Rule
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn

class UserPhotoVMTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val savedStateHandleMock: SavedStateHandle = mock {
        on { get<String>("photoId") } doReturn "1"
    }

    private val photoRepoMock: PhotoRepo = mock {
        onBlocking { getPhotoInfo(any()) } doReturn Resource.Success(
            PhotoInfoResponse(
                PhotoInfo(
                    id = "1",
                    secret = "2",
                    server = "3",
                    owner = Owner(
                        nsid = "",
                        username = "Dale1234",
                        realname = "Dale Evans",
                        location = "laurel road",
                        iconServer = "",
                        iconFarm = 1
                    ),
                    title = Title("image title"),
                    dates = Dates(0L, "12/12/2020"),
                )
            )
        )
    }

    private val peopleRepoMock: PeopleRepo = mock {
        onBlocking { getUsersPhotos(any()) } doReturn Resource.Success(
            PhotosResponse(
                Photos(
                    page = 1,
                    pages = 10,
                    perpage = 100,
                    total = 100,
                    photo = listOf(
                        Photo(
                            id = "1",
                            owner = "dale",
                            secret = "1",
                            title = "title",
                            server = "server",
                            iconServer = 1,
                            iconFarm = "farm",
                            ownerName = "dale",
                            "tag1, tag2"
                        )
                    )
                )
            )
        )
    }

    private val viewModel = UserPhotoVM(
        savedStateHandleMock,
        photoRepoMock,
        peopleRepoMock,
        mainDispatcherRule.testDispatcher
    )

    @Test
    fun photoUrlIsCorrect() = runTest {
        val result = viewModel.userPhotoScreenState
        assertThat(result.userPhoto[0].url, equalTo("https://live.staticflickr.com/server/1_1.jpg"))
    }

    @Test
    fun userInfoIsCorrect() = runTest {
        val result = viewModel.userPhotoScreenState
        assertThat(result.postedOn, equalTo("01/01/1970 - 01:00:00 GMT"))
        assertThat(result.title, equalTo("image title"))
        assertThat(result.realName, equalTo("Dale Evans"))
        assertThat(result.taken, equalTo("12/12/2020"))
    }
}