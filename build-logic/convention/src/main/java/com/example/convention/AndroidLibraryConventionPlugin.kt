package com.example.convention

//import org.gradle.api.Plugin
//import org.gradle.api.Project
//import org.gradle.kotlin.dsl.configure
//import org.gradle.kotlin.dsl.dependencies
//import org.gradle.kotlin.dsl.kotlin
//
//class AndroidLibraryConventionPlugin : Plugin<Project> {
//    override fun apply(target: Project) {
//        with(target) {
//            with(pluginManager) {
//                apply("com.android.library")
//                apply("org.jetbrains.kotlin.android")
//            }
//
//            extensions.configure<LibraryExtension> {
//                configureKotlinAndroid(this)
//                defaultConfig.targetSdk = 33
//            }
//        }
//    }
//}
//
//internal fun Project.configureKotlinAndroid(
//    commonExtension: CommonExtension<*, *, *, *>,
//) {
//
//    commonExtension.apply {
//        compileSdk = 33
//
//        defaultConfig {
//            minSdk = 21
//        }
//
//        compileOptions {
//            sourceCompatibility = JavaVersion.VERSION_1_8
//            targetCompatibility = JavaVersion.VERSION_1_8
//            isCoreLibraryDesugaringEnabled = true
//        }
//    }
//
//    //version catalog
//    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
//
//    dependencies {
//        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
//    }
//
//}