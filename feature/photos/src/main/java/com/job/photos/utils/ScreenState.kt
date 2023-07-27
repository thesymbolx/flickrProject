package com.job.photos.utils

sealed class ScreenState {
    object Success : ScreenState()
    object Error: ScreenState()
}