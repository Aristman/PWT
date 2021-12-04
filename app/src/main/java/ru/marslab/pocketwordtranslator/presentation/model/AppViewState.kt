package ru.marslab.pocketwordtranslator.presentation.model

sealed class AppViewState<out T, out E> {
    object Init : AppViewState<Nothing, Nothing>()
    data class Success<out T>(val data: T) : AppViewState<T, Nothing>()
    data class Error<out E>(val error: E) : AppViewState<Nothing, E>()
    data class Loading(val progress: Int?) : AppViewState<Nothing, Nothing>()
}
