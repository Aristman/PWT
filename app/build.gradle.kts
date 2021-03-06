plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    id("com.github.ben-manes.versions") version "0.39.0" // ./gradlew dependencyUpdates
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "ru.marslab.pocketwordtranslator"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.jetpackCompose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(Dependencies.Jetpack.CORE)
    implementation(Dependencies.Jetpack.CONSTRAINT_LAYOUT)
    implementation(Dependencies.Jetpack.LIFECYCLE)
    implementation(Dependencies.Jetpack.APPCOMPAT)
    implementation(Dependencies.Ui.MATERIAL)
    implementation(Dependencies.Jetpack.ACTIVITY)
    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.ANDROID_JUNIT)
    androidTestImplementation(Dependencies.Test.ANDROID_ESPRESSO)
    // Serialization
    implementation(Dependencies.Serialization.KOTLIN_SERIALIZATION_JSON)
    // Retrofit
    implementation(Dependencies.Retrofit.CORE)
    implementation(Dependencies.Retrofit.GSON_CONVERTER)
    implementation(Dependencies.Retrofit.RXJAVA2)
    // OkHttp
    implementation(Dependencies.OkHttp.CORE)
    implementation(Dependencies.OkHttp.LOGGER)
    // RxJava2
    implementation(Dependencies.RxJava2.CORE)
    implementation(Dependencies.RxJava2.ANDROID)
    // Koin
    implementation(Dependencies.Koin.CORE)
    implementation(Dependencies.Koin.ANDROID)
    implementation(Dependencies.Koin.ANDROID_COMPOSE)
    // Jetpack compose
    implementation(Dependencies.JetpackCompose.JC_UI)
    implementation(Dependencies.JetpackCompose.JC_UI_PREVIEW)
    implementation(Dependencies.JetpackCompose.JC_MATERIAL)
    androidTestImplementation(Dependencies.JetpackCompose.JC_TEST_JUNIT)
    debugImplementation(Dependencies.JetpackCompose.JC_UI_DEBUG)
    debugImplementation(Dependencies.JetpackCompose.JC_UI_TEST_DEBUG)
    // Accompanist
    implementation(Dependencies.Accompanist.NAVIGATION)
    // ExoPlayer
    implementation(Dependencies.ExoPlayer.CORE)
    // Room
    implementation(Dependencies.Room.CORE)
    annotationProcessor(Dependencies.Room.ANNOTATION_PROCESSOR)
    kapt(Dependencies.Room.KAPT_COMPILER)
    implementation(Dependencies.Room.RXJAVA2)
    // Glide-jetpack-compose
    implementation(Dependencies.Glide.JETPACK_COMPOSE)
    implementation(project(":shared"))
    implementation(project(":marsbaselibrary"))
}
