plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

apply {
    from("${rootProject.projectDir}/common.gradle")
}

android {
    namespace = "com.job.photos"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    testImplementation(project(":core:common"))

    implementation(libs.glide.compose)
    implementation(libs.coil.compose)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.compiler)

    implementation(libs.bundles.network)
    implementation(libs.bundles.paging)
}