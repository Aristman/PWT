plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    linuxX64()
    mingwX64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor
                implementation(Dependencies.Ktor.core)
                implementation(Dependencies.Ktor.logging)
                implementation(Dependencies.Ktor.contentNegotiation)
                implementation(Dependencies.Ktor.serializationJson)

                // Serialization
                implementation(Dependencies.Kotlin.serialization)

                implementation(Dependencies.SqlDelight.core)
                implementation(Dependencies.SqlDelight.coroutines)

                // coroutines
                implementation(Dependencies.Kotlin.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Ktor.okhttp)
                implementation(Dependencies.Ktor.android)
                implementation(Dependencies.SqlDelight.androidDriver)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val linuxX64Main by getting
        val linuxMain by creating {
            dependsOn(commonMain)
            linuxX64Main.dependsOn(this)
        }
        val linuxX64Test by getting
        val linuxTest by creating {
            dependsOn(commonTest)
            linuxX64Test.dependsOn(this)
        }
        val mingwX64Main by getting
        val windowsMain by creating {
            dependsOn(commonMain)
            mingwX64Main.dependsOn(this)
        }
        val mingwX64Test by getting
        val windowsTest by creating {
            dependsOn(commonTest)
            mingwX64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = AppConfig.completeSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "ru.marslab.pocketwordtranslator.shared.db"
    }
}
