plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("plugin.serialization")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
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
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
//    implementation("com.squareup.okhttp3:okhttp:$retrofitVersion")

    //RxJava2
    implementation("io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    //Hilt
    implementation("com.google.dagger:hilt-android:${Versions.hiltVersion}")
    annotationProcessor("com.google.dagger:hilt-compiler:${Versions.hiltVersion}")
    kapt("com.google.dagger:hilt-compiler:${Versions.hiltVersion}")

    //Jetpack compose
    implementation("androidx.compose.ui:ui:${Versions.composeVersion}")
    implementation("androidx.compose.material:material:${Versions.composeVersion}")
    implementation("androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}")
    debugImplementation("androidx.compose.ui:ui-tooling:${Versions.composeVersion}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${Versions.composeVersion}")
    //Accompanist
    implementation("com.google.accompanist:accompanist-navigation-material:${Versions.accompanistVersion}")
}