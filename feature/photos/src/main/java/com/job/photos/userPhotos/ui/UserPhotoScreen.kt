package com.job.photos.userPhotos.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.job.photos.R
import com.job.photos.userPhotos.UserPhotoVM
import com.job.photos.utils.ScreenState
import com.job.ui.theme.composables.ErrorDialog

@Composable
fun UserPhotoScreen(userPhotoVM: UserPhotoVM) {
    if(userPhotoVM.userPhotoScreenState.screenState == ScreenState.Error) {
        ErrorDialog()
    }

    UserPhotoScreen(userPhotoVM.userPhotoScreenState)
}

@Composable
fun UserPhotoScreen(userPhotoInfoScreenState: PhotoInfoScreenState) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            AsyncImage(
                model = userPhotoInfoScreenState.photoUrl,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_loading),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        item {
            Text(
                text = userPhotoInfoScreenState.title,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp)
            )
        }

        userPhotoInfoScreenState.realName?.let {
            item {
                Text(
                    text = userPhotoInfoScreenState.realName,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                )
            }
        }

        userPhotoInfoScreenState.taken?.let {
            item {
                Text(
                    text = userPhotoInfoScreenState.taken,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                )
            }
        }


        userPhotoInfoScreenState.postedOn?.let {
            item {
                Text(
                    text = userPhotoInfoScreenState.postedOn,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                )

            }
        }

        itemsIndexed(userPhotoInfoScreenState.userPhoto) { index, item ->
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.url)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().let {
                    if (index == 0) it.padding(top = 16.dp) else it
                }
            )
        }
    }


}