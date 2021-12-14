package ru.marslab.pocketwordtranslator.presentation.viewmodels

import kotlinx.coroutines.flow.StateFlow
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.model.HistoryWordUi

interface HistoryViewModel {
    val historyList: StateFlow<AppViewState<List<HistoryWordUi>, Throwable>>
    fun loadHistory()
}