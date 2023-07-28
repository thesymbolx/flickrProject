plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply {
    from("${rootProject.projectDir}/common.gradle")
}

android {
    namespace = "com.job.ui"
}