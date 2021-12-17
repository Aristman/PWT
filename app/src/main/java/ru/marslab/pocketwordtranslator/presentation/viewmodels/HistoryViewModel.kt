package ru.marslab.pocketwordtranslator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.domain.interactor.HistoryInteractor
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.model.HistoryWordUi
import ru.marslab.pocketwordtranslator.presentation.toUi

class HistoryViewModel (
    private val historyInteractor: HistoryInteractor
) : ViewModel() {
    private val _historyList =
        MutableStateFlow<AppViewState<List<HistoryWordUi>, Throwable>>(AppViewState.Init)
    val historyList: StateFlow<AppViewState<List<HistoryWordUi>, Throwable>> =
        _historyList.asStateFlow()

    fun loadHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            historyInteractor.loadHistory()
                .doOnSubscribe {
                    _historyList.tryEmit(AppViewState.Loading(null))
                }
                .map {
                    it.toUi()
                }
                .toList()
                .subscribe(
                    {
                        _historyList.tryEmit(AppViewState.Success(it))
                    },
                    {
                        _historyList.tryEmit(AppViewState.Error(it))
                    }
                )
        }
    }
}
