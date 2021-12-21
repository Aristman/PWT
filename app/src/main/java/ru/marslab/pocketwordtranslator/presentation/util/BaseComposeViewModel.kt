package ru.marslab.pocketwordtranslator.presentation.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.marslab.pocketwordtranslator.presentation.model.AppState

abstract class BaseComposeViewModel<D : Any, E : Any> : ViewModel() {
    var uiState by mutableStateOf<AppState<D, E>>(AppState.Init)
        private set

    fun setLoading(progress: Int? = null) {
        uiState = AppState.Loading(progress)
    }

    fun setSuccessful(data: D) {
        uiState = AppState.Success(data)
    }

    fun setError(error: E) {
        uiState = AppState.Error(error)
    }
}
