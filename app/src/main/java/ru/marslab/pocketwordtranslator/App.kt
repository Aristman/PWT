package ru.marslab.pocketwordtranslator

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.marslab.pocketwordtranslator.di.okHttpModule

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(okHttpModule)
        }
    }

}
