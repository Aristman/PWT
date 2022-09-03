package ru.marslab.pocketwordtranslator

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import ru.marslab.pocketwordtranslator.navigation.mainModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ScreenRegistry {
            mainModule()
        }
    }
}
