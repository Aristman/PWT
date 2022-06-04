package ru.marslab.pocketwordtranslator.core

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseWidgetModel<D, AC : Action>(initValue: D) {
    private val _state = MutableStateFlow(initValue)
    val state: StateFlow<D> = _state.asStateFlow()

    private val _action = MutableSharedFlow<AC>(extraBufferCapacity = 1)
    val action: SharedFlow<AC>
        get() = _action.asSharedFlow()

    infix fun sendAction(action: AC) {
        _action.tryEmit(action)
    }

    fun setState(state: () -> D) {
        _state.value = state()
    }
}
