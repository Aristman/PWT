package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable
import ru.marslab.shared.domain.model.HistoryWord

interface HistoryInteractor {
    suspend fun loadHistory(): Observable<HistoryWord>
}
