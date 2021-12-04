package ru.marslab.pocketwordtranslator.presentation.model

sealed class AppViewState<out D, out E> {
    object Init : AppViewState<Nothing, Nothing>()
    data class Success<out D>(val data: D) : AppViewState<D, Nothing>()
    data class Error<out E>(val error: E) : AppViewState<Nothing, E>()
    data class Loading(val progress: Int?) : AppViewState<Nothing, Nothing>()
}
