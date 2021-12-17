package ru.marslab.pocketwordtranslator.domain.interactor

import io.reactivex.Observable
import io.reactivex.Single
import ru.marslab.pocketwordtranslator.domain.model.HistoryWord

interface HistoryInteractor {
    suspend fun loadHistory(): Observable<HistoryWord>
}