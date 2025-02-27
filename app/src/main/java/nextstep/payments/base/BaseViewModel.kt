package nextstep.payments.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE : ScreenState, EVENT : ScreenEvent, SIDE_EFFECT : ScreenSideEffect>
    : ViewModel() {
    abstract fun initState(): STATE

    private val _state = MutableStateFlow(initState())
    val state: StateFlow<STATE> = _state.asStateFlow()

    private val _event = MutableSharedFlow<EVENT>()

    private val _sideEffect: MutableSharedFlow<SIDE_EFFECT> = MutableSharedFlow()
    val sideEffect: SharedFlow<SIDE_EFFECT> = _sideEffect.asSharedFlow()

    init {
        _event
            .onEach { handleEvent(it) }
            .launchIn(viewModelScope)
    }

    fun currentState(): STATE = _state.value

    abstract fun handleEvent(event: EVENT)

    fun updateState(state: STATE) {
        _state.value = state
    }

    fun sendEvent(event: EVENT) {
        viewModelScope.launch { _event.emit(event) }
    }

    fun sendSideEffect(sideEffect: SIDE_EFFECT) {
        viewModelScope.launch { _sideEffect.emit(sideEffect) }
    }
}
