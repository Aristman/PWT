@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
rootProject.name = "Pocket Word Translator"
include(":app")
include(":core")
include(":shared")
include(":data")
include(":domain")
include(":presentation")
