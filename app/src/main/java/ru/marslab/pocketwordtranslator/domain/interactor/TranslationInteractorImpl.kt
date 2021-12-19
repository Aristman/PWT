package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable
import ru.marslab.pocketwordtranslator.domain.repository.DatabaseRepository
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository
import ru.marslab.shared.domain.model.HistoryWord
import ru.marslab.shared.domain.model.Translations

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
