package ru.marslab.pocketwordtranslator

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.marslab.pocketwordtranslator.data.NetworkRepositoryImpl
import ru.marslab.pocketwordtranslator.domain.interactor.Interactor
import ru.marslab.pocketwordtranslator.domain.interactor.InteractorImpl
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
