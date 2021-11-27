package com.example.poketwordtranslator.domain.interactor

import com.example.poketwordtranslator.domain.model.Translations
import com.example.poketwordtranslator.domain.repository.NetworkRepository
import io.reactivex.Observable

class InteractorImpl(
    private val networkRepository: NetworkRepository
) : Interactor<Translations> {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<Translations> =
        networkRepository.getTranslations(word)
}