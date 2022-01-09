package ru.marslab.shared.domain.interactor

import io.reactivex.Observable
import ru.marslab.shared.domain.model.HistoryWord
import ru.marslab.shared.domain.model.Translations

interface TranslationInteractor {
    fun getData(word: String, fromRemoteSource: Boolean): Observable<Translations>
    suspend fun saveToHistory(word: HistoryWord)
}
