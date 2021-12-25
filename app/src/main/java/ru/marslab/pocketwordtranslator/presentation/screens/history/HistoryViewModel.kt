package ru.marslab.pocketwordtranslator.presentation.screens.history

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.marsbaselibrary.BaseComposeViewModel
import ru.marslab.pocketwordtranslator.presentation.model.HistoryUiState
import ru.marslab.pocketwordtranslator.presentation.toDomain
import ru.marslab.pocketwordtranslator.presentation.toUiState
import ru.marslab.shared.domain.interactor.HistoryInteractor

class HistoryViewModel(
    private val historyInteractor: HistoryInteractor
) : BaseComposeViewModel<List<HistoryUiState>, Throwable>() {

    fun loadHistory() {
        viewModelScope.launch(Dispatchers.IO) {
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

    fun deleteWord(word: HistoryUiState) {
        viewModelScope.launch(Dispatchers.IO) {
            historyInteractor.deleteWord(word.toDomain())
        }
    }
}
