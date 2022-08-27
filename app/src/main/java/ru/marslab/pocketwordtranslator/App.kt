package ru.marslab.pocketwordtranslator

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import dagger.hilt.android.HiltAndroidApp
import ru.marslab.pocketwordtranslator.navigation.mainModule

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ScreenRegistry {
            mainModule()
        }
    }
}
