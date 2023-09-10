plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

apply {
    from("${rootProject.projectDir}/common.gradle")
}

android {
    namespace = "com.job.common"
}

dependencies {
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.compiler)
    implementation(libs.bundles.test)

    implementation(platform(libs.firebase))
    implementation(libs.firebase.realtime.database)
}