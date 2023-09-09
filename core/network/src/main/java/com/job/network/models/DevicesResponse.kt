package com.job.network.models


data class DevicesResponse(
    var devices: List<Device>,
    var totalPages: Int
)

data class Device(
    var id: Int?,
    var name: String?,
    var email: String?,
    var code: String?,
    var enrolled: Boolean?,
    var added: Long?,
    var secure: Boolean?,
    var screenLock: Boolean?,
    var biometrics: Boolean?,
    var version: String?,
    var latestAppVersion: String?,
    var isLatestVersion: Boolean?,
    var guidesCompleted: String?,
    var device: String?,
    var os: String?,
    var osVersionState: String?,
    var latestOSVersion: String?,
    var platform: String?,
    var isWorkProfile: Boolean?,
    var latestInsecureScanDate: String?,
    var latestScanDate: String?
)