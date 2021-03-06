package ru.marslab.pocketwordtranslator

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.marslab.pocketwordtranslator.di.databaseModule
import ru.marslab.pocketwordtranslator.di.historyScreen
import ru.marslab.pocketwordtranslator.di.okHttpModule
import ru.marslab.pocketwordtranslator.di.repositoryModule
import ru.marslab.pocketwordtranslator.di.retrofitModule
import ru.marslab.pocketwordtranslator.di.translationScreen

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                okHttpModule,
                retrofitModule,
                repositoryModule,
                databaseModule,
                historyScreen,
                translationScreen
            )
        }
    }
}
