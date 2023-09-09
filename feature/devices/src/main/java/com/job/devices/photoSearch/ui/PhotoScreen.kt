package com.job.devices.photoSearch.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.job.devices.photoSearch.DevicesVM
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.job.network.models.Device
import com.job.ui.theme.composables.ErrorDialog


@Composable
fun DevicesScreen(devicesVM: DevicesVM) {
    val items: LazyPagingItems<Device> = devicesVM.devices.collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            items.loadState.refresh is LoadState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                )
            }

            items.loadState.refresh is LoadState.Error -> ErrorDialog()
            items.loadState.append is LoadState.Error -> ErrorDialog()
        }
    }
    DevicesScreen(items)
}

@Composable
fun DevicesScreen(
    devices: LazyPagingItems<Device>,
) {
    LazyColumn {
        items(
            count = devices.itemCount,
            key = devices.itemKey(),
            contentType = devices.itemContentType()
        )
        { index ->
            devices[index]?.let {
                Device(it)
            }
        }
    }
}

@Composable
private fun Device(device: Device){
    Divider(modifier = Modifier.fillMaxWidth().height(3.dp))
    Text(text = "$device")
    Divider(modifier = Modifier.fillMaxWidth().height(3.dp))
}