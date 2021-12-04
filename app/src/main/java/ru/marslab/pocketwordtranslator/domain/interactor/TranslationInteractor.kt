package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable
import ru.marslab.pocketwordtranslator.domain.model.Translations

interface TranslationInteractor {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<Translations>
}