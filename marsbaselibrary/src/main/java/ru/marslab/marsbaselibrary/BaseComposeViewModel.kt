package ru.marslab.marsbaselibrary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class BaseComposeViewModel<D : Any, E : Any> : ViewModel() {
    var uiState by mutableStateOf<AppState<D, E>>(AppState.Init)
        private set

    fun setLoadingState(progress: Float? = null) {
        uiState = AppState.Loading(progress)
    }

    fun setSuccessfulState(data: D) {
        uiState = AppState.Success(data)
    }

    fun setErrorState(error: E) {
        uiState = AppState.Error(error)
    }
}
