package com.job.jobApplication.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.job.jobApplication.R
import com.job.photos.photoSearch.PhotoVM
import com.job.photos.photoSearch.ui.PhotoSearchScreen
import com.job.photos.userPhotos.UserPhotoVM
import com.job.photos.userPhotos.ui.UserPhotoScreen
import com.job.ui.theme.DarkLateGray
import com.job.ui.theme.Grayscale
import com.job.ui.theme.JobApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityScreen() {
    val navController = rememberNavController()

    JobApplicationTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(id = R.string.app_bar_title)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = DarkLateGray,
                        titleContentColor = Color.White
                    )
                )
            }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = Grayscale
            ) {
                NavHost(navController = navController, startDestination = "photoSearch") {
                    composable("photoSearch") {
                        val viewModel = hiltViewModel<PhotoVM>()
                        PhotoSearchScreen(viewModel, navController)
                    }
                    composable("userPhoto/{photoId}") {
                        val viewModel = hiltViewModel<UserPhotoVM>()
                        UserPhotoScreen(viewModel)
                    }
                }
            }
        }
    }
}