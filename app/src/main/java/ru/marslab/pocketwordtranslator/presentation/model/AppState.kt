package ru.marslab.pocketwordtranslator.presentation.model

sealed class AppState<out D, out E> {
    object Init : AppState<Nothing, Nothing>()
    data class Success<out D>(val data: D) : AppState<D, Nothing>()
    data class Error<out E>(val error: E) : AppState<Nothing, E>()
    data class Loading(val progress: Int?) : AppState<Nothing, Nothing>()
}
