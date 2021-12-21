package ru.marslab.pocketwordtranslator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.presentation.model.AppState
import ru.marslab.pocketwordtranslator.presentation.model.HistoryUiState
import ru.marslab.pocketwordtranslator.presentation.toUiState
import ru.marslab.shared.domain.interactor.HistoryInteractor

class HistoryViewModel(
    private val historyInteractor: HistoryInteractor
) : ViewModel() {
    private val _historyList =
        MutableStateFlow<AppState<List<HistoryUiState>, Throwable>>(AppState.Init)
    val historyList: StateFlow<AppState<List<HistoryUiState>, Throwable>> =
        _historyList.asStateFlow()

    fun loadHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            historyInteractor.loadHistory()
                .doOnSubscribe {
                    _historyList.tryEmit(AppState.Loading(null))
                }
                .map {
                    it.toUiState()
                }
                .toList()
                .subscribe(
                    {
                        _historyList.tryEmit(AppState.Success(it))
                    },
                    {
                        _historyList.tryEmit(AppState.Error(it))
                    }
                )
        }
    }
}
