plugins {
    id("com.android.application")
    id("kotlin-parcelize")
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
        kotlinCompilerExtensionVersion = Versions.composeVersion
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

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinSerializationVersion}")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofitVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Versions.okHttpVersion}")
    implementation("com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}")

    //RxJava2
    implementation("io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    //Koin
    implementation("io.insert-koin:koin-core:${Versions.koinVersion}")
    implementation("io.insert-koin:koin-android:${Versions.koinVersion}")
    implementation ("io.insert-koin:koin-androidx-compose:${Versions.koinVersion}")

    //Jetpack compose
    implementation("androidx.compose.ui:ui:${Versions.composeVersion}")
    implementation("androidx.compose.material:material:${Versions.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.composeVersion}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.composeVersion}")
    //Accompanist
    implementation("com.google.accompanist:accompanist-navigation-material:${Versions.accompanistVersion}")
    //ExoPlayer
    implementation("com.google.android.exoplayer:exoplayer:${Versions.exoPlayerVersion}")
    //Room
    implementation("androidx.room:room-runtime:${Versions.roomVersion}")
    annotationProcessor("androidx.room:room-compiler:${Versions.roomVersion}")
    kapt("androidx.room:room-compiler:${Versions.roomVersion}")
    implementation("androidx.room:room-rxjava2:${Versions.roomVersion}")
    //Glide
    implementation("com.github.skydoves:landscapist-glide:${Versions.glideVersion}")
}