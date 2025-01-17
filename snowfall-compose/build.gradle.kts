@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("com.vanniktech.maven.publish")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    metadata {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    freeCompilerArgs.add("-Xexplicit-api=strict")
                }
            }
        }
    }

    // Android
    androidTarget {
        publishAllLibraryVariants()
    }

    // JVM
    jvm()

    // JS
    js {
        browser()
    }

    // MacOS
    macosX64()
    macosArm64()

    // iOS
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    // WASM
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.foundation)
                implementation(compose.ui)
            }
        }
    }

    jvmToolchain(17)
}

android {
    namespace = "io.github.skeptick.snowfall.compose"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }

    buildFeatures {
        buildConfig = false
    }
}