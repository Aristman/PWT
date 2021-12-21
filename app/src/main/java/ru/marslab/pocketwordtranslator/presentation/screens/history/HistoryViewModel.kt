package ru.marslab.pocketwordtranslator.presentation.screens.history

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.presentation.model.HistoryUiState
import ru.marslab.pocketwordtranslator.presentation.toUiState
import ru.marslab.pocketwordtranslator.presentation.util.BaseComposeViewModel
import ru.marslab.shared.domain.interactor.HistoryInteractor

class HistoryViewModel(
    private val historyInteractor: HistoryInteractor
) : BaseComposeViewModel<List<HistoryUiState>, Throwable>() {

    fun loadHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            historyInteractor.loadHistory()
                .doOnSubscribe {
                    setLoadingState()
                }
                .map {
                    it.toUiState()
                }
                .toList()
                .subscribe(
                    {
                        setSuccessfulState(it)
                    },
                    {
                        setErrorState(it)
                    }
                )
        }
    }
}
