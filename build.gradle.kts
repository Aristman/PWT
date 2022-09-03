buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Dependencies.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Dependencies.Kotlin.version}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Dependencies.Kotlin.version}")
        classpath("com.squareup.sqldelight:gradle-plugin:${Dependencies.SqlDelight.version}")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:${Dependencies.Dagger.version}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
