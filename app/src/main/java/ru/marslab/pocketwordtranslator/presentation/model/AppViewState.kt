package ru.marslab.pocketwordtranslator.presentation.model

sealed class AppViewState {
    data class Success<out T>(val data: T?) : AppViewState()
    data class Error(val error: Throwable) : AppViewState()
    data class Loading(val progress: Int?) : AppViewState()
}
