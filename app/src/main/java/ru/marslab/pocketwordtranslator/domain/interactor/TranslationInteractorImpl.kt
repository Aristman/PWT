package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable
import ru.marslab.shared.domain.model.HistoryWord
import ru.marslab.shared.domain.model.Translations
import ru.marslab.shared.domain.repository.DatabaseRepository
import ru.marslab.shared.domain.repository.NetworkRepository

class TranslationInteractorImpl(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository
) : TranslationInteractor {
    override fun getData(word: String, fromRemoteSource: Boolean): Observable<Translations> =
        networkRepository.getTranslations(word)

    override suspend fun saveToHistory(word: HistoryWord) {
        databaseRepository.saveToHistory(word)
    }
}
