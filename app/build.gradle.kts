plugins {
    id("com.android.application")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization")
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
    }
}

val retrofitVersion = Versions.RETROFIT_VERSION
val rxjavaVersion = Versions.RXJAVA_VERSION
val okhttpVersion = Versions.OKHTTP_VERSION
val serializationVersion = Versions.KOTLIN_SERIALIZATION_VERSION

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")
//    implementation("com.squareup.okhttp3:okhttp:$retrofitVersion")

    //RxJava2
    implementation("io.reactivex.rxjava2:rxjava:$rxjavaVersion")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
}