package com.job.photos.ui


import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.job.photos.PhotoVM
import com.job.photos.R
import com.job.ui.theme.DarkLateGray
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.job.network.models.Photo


@Composable
fun PhotoScreen(photoVM: PhotoVM = viewModel()) {
    val items: LazyPagingItems<Photo> = photoVM.photos.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            items.loadState.refresh is LoadState.Loading -> {
                CircularProgressIndicator(modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center))
            }
            items.loadState.refresh is LoadState.Error -> ErrorDialog()
            items.loadState.append is LoadState.Error -> ErrorDialog()
        }
    }
    PhotoScreen(items)
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PhotoScreen(photos: LazyPagingItems<Photo>) {
    var showInfo by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(200.dp)
        ) {
            items(
                count = photos.itemCount,
                key = photos.itemKey(),
                contentType = photos.itemContentType()
            )
            { index ->
                val photo = photos[index]
                
                if (photo != null) {
                    ExpandableInfoPhoto(
                        url = stringResource(
                            id = R.string.photoUrl,
                            photo.server,
                            photo.id,
                            photo.secret
                        ),
                        expanded = showInfo,
                        ownerName = photo.ownerName,
                        tags = photo.tags,
                        userIconUrl = if (photo.iconServer > 0) {
                            stringResource(
                                id = R.string.buddyIcon,
                                photo.iconFarm,
                                photo.iconServer,
                                photo.owner
                            )
                        } else {
                            stringResource(id = R.string.noBuddyIcon)
                        }
                    )
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            containerColor = DarkLateGray,
            onClick = { showInfo = !showInfo }
        ) {
            Text(text = stringResource(id = R.string.info), color = Color.White, fontSize = 18.sp)
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
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
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(16.dp)
                )


            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        if (expanded) {
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, end = 8.dp),
                )
            }

            if (!tags.isNullOrEmpty()) {
                Text(
                    text = "Tags: $tags",
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ErrorDialog() {
    val openDialog = remember { mutableStateOf(false)  }

    AlertDialog(
        onDismissRequest = {
            openDialog.value = false
        },
        title = {
            Text(text = stringResource(id = R.string.errorDialogTitle))
        },
        text = {
            Text(stringResource(id = R.string.errorDialogMessage))
        },
        confirmButton = {
            Button(

                onClick = {
                    openDialog.value = false
                }) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )
}