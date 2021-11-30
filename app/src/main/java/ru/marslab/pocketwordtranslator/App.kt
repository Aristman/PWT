package ru.marslab.pocketwordtranslator

import android.app.Application
import ru.marslab.pocketwordtranslator.data.NetworkRepositoryImpl
import ru.marslab.pocketwordtranslator.domain.interactor.Interactor
import ru.marslab.pocketwordtranslator.domain.interactor.InteractorImpl
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository

class App : Application() {
    companion object {
        var instance: Application? = null
            private set
    }

    private val networkRepository: NetworkRepository =
        NetworkRepositoryImpl()

    val mainInteractor: Interactor<Translations> =
        InteractorImpl(networkRepository)

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}