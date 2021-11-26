package com.example.poketwordtranslator.presentation.model

sealed class ViewState {
    data class Success<out T>(val data: T?) : ViewState()
    data class Error(val error: Throwable) : ViewState()
    data class Loading(val progress: Int?) : ViewState()
}
