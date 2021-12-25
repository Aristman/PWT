package ru.marslab.shared.domain.interactor

import io.reactivex.Observable
import ru.marslab.shared.domain.model.HistoryWord

interface HistoryInteractor {
    suspend fun loadHistory(): Observable<HistoryWord>
    suspend fun deleteWord(historyWord: HistoryWord)
}
