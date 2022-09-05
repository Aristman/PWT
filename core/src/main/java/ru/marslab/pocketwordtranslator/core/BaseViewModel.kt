package ru.marslab.pocketwordtranslator.core

import android.util.Log
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val ERROR_LOG_TAG = "ViewModel Error"

@Stable
abstract class BaseViewModel<ST, EV : Event, AC : Action>(
    initState: ST,
    eventBufferCapacity: Int = 1
) : ViewModel() {
    protected open val widgets: List<BaseWidgetModel<*, out Action>> = emptyList()
    private val _state = MutableStateFlow(initState)
    val state: StateFlow<ST>
        get() = _state.asStateFlow()

    private val action = MutableSharedFlow<AC>()

    private val _event = MutableSharedFlow<EV?>(extraBufferCapacity = eventBufferCapacity)
    val event: SharedFlow<EV?>
        get() = _event.asSharedFlow()

    protected var navigator: Navigator? = null
        private set
        get() = checkNotNull(field)

    init {
        this.action
            .onEach { _state.tryEmit(reduceStateByAction(state.value, it)) }
            .stateIn(viewModelScope, SharingStarted.Eagerly, initState)
    }

    fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    infix fun sendAction(action: AC) {
        launch {
            this@BaseViewModel.action.emit(action)
        }
    }

    fun popUp() {
        navigator?.pop()
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            block(this)
        }
    }

    protected open fun handleError(error: Throwable, message: String? = null) {
        Log.d(ERROR_LOG_TAG, message ?: error.message.orEmpty(), error)
    }

    protected fun sendEvent(event: EV, dismissDelay: Long? = null) {
        launch {
            _event.emit(event)
            dismissDelay?.let {
                delay(it)
                _event.emit(null)
            }
        }
    }

    protected fun reduceState(reduceBlock: () -> ST) {
        _state.tryEmit(reduceBlock())
    }

    protected abstract fun reduceStateByAction(currentState: ST, action: AC): ST

    @Suppress("UNCHECKED_CAST")
    protected fun List<BaseWidgetModel<*, out Action>>.actionObserve() = this.also { widgets ->
        widgets.map { it.action }
            .merge()
            .onEach { action ->
                (action as? AC)?.let { this@BaseViewModel sendAction action }
            }
            .shareIn(viewModelScope, SharingStarted.Eagerly)
    }
}
