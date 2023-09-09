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
import com.job.devices.photoSearch.DevicesVM
import com.job.devices.photoSearch.ui.DevicesScreen
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
                    .padding(it)
            ) {
                NavHost(navController = navController, startDestination = "photoSearch") {
                    composable("photoSearch") {
                        val viewModel = hiltViewModel<DevicesVM>()
                        DevicesScreen(viewModel)
                    }
                }
            }
        }
    }
}