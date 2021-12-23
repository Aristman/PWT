package ru.marslab.marsbaselibrary

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.marslab.marsbaselibrary.AppState.Error
import ru.marslab.marsbaselibrary.AppState.Init
import ru.marslab.marsbaselibrary.AppState.Loading
import ru.marslab.marsbaselibrary.AppState.Success

abstract class BaseViewModel<D : Any, E : Any> : ViewModel() {
    private val _uiState: MutableStateFlow<AppState<D, E>> = MutableStateFlow(Init)
    val uiState: StateFlow<AppState<D, E>> =
        _uiState.asStateFlow()

    fun setLoading(progress: Float? = null) {
        _uiState.tryEmit(Loading(progress))
    }

    fun setSuccessful(data: D) {
        _uiState.tryEmit(Success(data))
    }

    fun setError(error: E) {
        _uiState.tryEmit(Error(error))
    }
}
