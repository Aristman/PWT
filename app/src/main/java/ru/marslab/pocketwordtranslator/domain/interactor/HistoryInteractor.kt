package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable
import ru.marslab.pocketwordtranslator.domain.model.HistoryWord

interface HistoryInteractor {
    suspend fun loadHistory(): Observable<HistoryWord>
    suspend fun deleteWord(historyWord: HistoryWord)
}
