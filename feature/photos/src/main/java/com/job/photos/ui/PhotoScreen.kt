package com.job.photos.ui


import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.job.photos.PhotoVM
import com.job.photos.R
import com.job.ui.theme.Grayscale

@Preview
@Composable
fun PhotoScreenPreview() {
    PhotoScreen(PhotoScreenState(emptyList()))
}

@Composable
fun PhotoScreen(photoVM: PhotoVM = viewModel()) {
    PhotoScreen(photoVM.photoScreenState)
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PhotoScreen(photoScreenState: PhotoScreenState) {
    var showInfo by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Grayscale)) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(200.dp)
        ) {
            items(key = { item -> item.key }, items = photoScreenState.photos) { photo ->
                ExpandableInfoPhoto(
                    url = photo.url,
                    expanded = showInfo,
                    ownerName = photo.ownerName,
                    tags = photo.tags,
                    userIconUrl = photo.userIconUrl
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            onClick = { showInfo = !showInfo }
        ) {
            Image(painter = painterResource(id = R.drawable.ic_info), contentDescription = null)
        }
    }
}

@Composable
fun ExpandableInfoPhoto(
    url: String,
    userIconUrl: String,
    expanded: Boolean,
    ownerName: String,
    tags: String
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .animateContentSize(
            animationSpec = tween(
                easing = LinearOutSlowInEasing
            )
        )) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            loading = {
                Image(
                    painter = painterResource(id = R.drawable.ic_loading),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp)
                )


            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Log.d("booboo", "$userIconUrl")

        if(expanded) {
            Row(modifier = Modifier.fillMaxWidth()) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(userIconUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(8.dp)
                )

                Text(
                    text = ownerName,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Text(
                text = tags,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            )
        }
    }
}