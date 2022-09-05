package ru.marslab.pocketwordtranslator.core

import android.util.Log
import androidx.compose.runtime.Stable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val ERROR_LOG_TAG = "ViewModel Error"

@Stable
abstract class BaseScreenModel<ST, EV : Event, AC : Action>(
    initState: ST,
    eventBufferCapacity: Int = 1
) : ScreenModel {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _state = MutableStateFlow(initState)
    val state: StateFlow<ST>
        get() = _state.asStateFlow()

    private val action = MutableSharedFlow<AC>()

    private val _event = MutableSharedFlow<EV>(extraBufferCapacity = eventBufferCapacity)
    val event: SharedFlow<EV>
        get() = _event.asSharedFlow()

    var navigator: Navigator? = null
        private set
        get() = checkNotNull(field)

    init {
        this.action
            .onEach { _state.tryEmit(reduceStateByAction(state.value, it)) }
            .stateIn(scope, SharingStarted.Eagerly, initState)
    }

    protected fun collectWidgetsActions(vararg widgets: BaseWidgetModel<*, AC>) {
        widgets.forEach { widget ->
            launch {
                widget.action.collect { sendAction(it) }
            }
        }
    }

    infix fun setNavigator(navigator: Navigator) {
        this.navigator = navigator
    }

    infix fun sendAction(action: AC) {
        launch {
            this@BaseScreenModel.action.emit(action)
        }
    }

    fun popUp() {
        navigator?.pop()
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        scope.launch {
            block(this)
        }
    }

    protected open fun handleError(error: Throwable, message: String? = null) {
        Log.d(ERROR_LOG_TAG, message ?: error.message.orEmpty(), error)
    }

    protected infix fun sendEvent(event: EV) {
        launch {
            _event.emit(event)
        }
    }

    protected fun reduceState(reduceBlock: () -> ST) {
        _state.tryEmit(reduceBlock())
    }

    protected abstract fun reduceStateByAction(currentState: ST, action: AC): ST

    override fun onDispose() {
        super.onDispose()
        scope.cancel()
    }
}
