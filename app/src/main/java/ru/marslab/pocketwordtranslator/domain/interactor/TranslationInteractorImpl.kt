package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable
import ru.marslab.pocketwordtranslator.domain.DatabaseRepository
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository


class TranslationInteractorImpl(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository
) : TranslationInteractor {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<Translations> =
        networkRepository.getTranslations(word)
            .toList()
            .flattenAsObservable {
                databaseRepository.saveToHistory(it.first())
                it
            }

    override fun saveToHistory(word: Translations) {
        databaseRepository.saveToHistory(word)
    }
}