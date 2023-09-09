package com.job.devices.utils

sealed class ScreenState {
    object Success : ScreenState()
    object Error: ScreenState()
}