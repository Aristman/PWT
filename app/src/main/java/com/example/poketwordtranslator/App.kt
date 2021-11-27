package com.example.poketwordtranslator

import android.app.Application
import com.example.poketwordtranslator.data.NetworkRepositoryImpl
import com.example.poketwordtranslator.domain.interactor.Interactor
import com.example.poketwordtranslator.domain.interactor.InteractorImpl
import com.example.poketwordtranslator.domain.model.Translations
import com.example.poketwordtranslator.domain.repository.NetworkRepository

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