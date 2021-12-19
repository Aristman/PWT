object Dependencies {
    object Jetpack {
        const val CORE = "androidx.core:core-ktx:${Versions.jetpackCore}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val ACTIVITY = "androidx.activity:activity-compose:${Versions.activity}"
    }

    object Ui {
        const val MATERIAL = "com.google.android.material:material:${Versions.material}"
    }

    object JetpackCompose {
        const val JC_UI = "androidx.compose.ui:ui:${Versions.jetpackCompose}"
        const val JC_UI_PREVIEW =
            "androidx.compose.ui:ui-tooling-preview:${Versions.jetpackCompose}"
        const val JC_MATERIAL = "androidx.compose.material:material:${Versions.jetpackCompose}"
        const val JC_TEST_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.jetpackCompose}"
        const val JC_UI_DEBUG = "androidx.compose.ui:ui-tooling:${Versions.jetpackCompose}"
        const val JC_UI_TEST_DEBUG =
            "androidx.compose.ui:ui-test-manifest:${Versions.jetpackCompose}"
    }

    object Accompanist {
        const val NAVIGATION =
            "com.google.accompanist:accompanist-navigation-material:${Versions.accompanist}"
    }

    object Serialization {
        const val KOTLIN_SERIALIZATION_JSON =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
    }

    object Retrofit {
        const val CORE = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val RXJAVA2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }

    object OkHttp {
        const val CORE = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val LOGGER = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object RxJava2 {
        const val CORE = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
        const val ANDROID = "io.reactivex.rxjava2:rxandroid:${Versions.rxJavaAndroid}"
    }

    object Koin {
        const val CORE = "io.insert-koin:koin-core:${Versions.koin}"
        const val ANDROID = "io.insert-koin:koin-android:${Versions.koin}"
        const val ANDROID_COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Room {
        const val CORE = "androidx.room:room-runtime:${Versions.room}"
        const val ANNOTATION_PROCESSOR = "androidx.room:room-compiler:${Versions.room}"
        const val KAPT_COMPILER = "androidx.room:room-compiler:${Versions.room}"
        const val RXJAVA2 = "androidx.room:room-rxjava2:${Versions.room}"
    }

    object Glide {
        const val CORE = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val ANNOTATION_PROCESSOR = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val JETPACK_COMPOSE = "com.github.skydoves:landscapist-glide:${Versions.glideJC}"
    }

    object ExoPlayer {
        const val CORE = "com.google.android.exoplayer:exoplayer:${Versions.exoPlayer}"
    }
}
