package ru.marslab.pocketwordtranslator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.presentation.model.AppAction

open class AppActionViewModel : ViewModel() {
    private val _appAction = MutableStateFlow<AppAction>(AppAction.Hide)
    val appAction: StateFlow<AppAction> = _appAction

    fun viewShow() {
        viewModelScope.launch {
            _appAction.tryEmit(AppAction.Show)
        }
    }

    fun viewHide() {
        viewModelScope.launch {
            _appAction.tryEmit(AppAction.Hide)
        }
    }
}