package org.sopt.and.core.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, SideEffect : UiSideEffect, Intent : UiEvent>() :
    ViewModel() {
    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _state = MutableStateFlow<State>(initialState)
    val uiState: StateFlow<State>
        get() = _state.asStateFlow()
    val currentState: State
        get() = uiState.value

    private val _event: MutableSharedFlow<Intent> = MutableSharedFlow()
    val event: SharedFlow<Intent>
        get() = _event.asSharedFlow()

    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect: Flow<SideEffect>
        get() = _sideEffect.receiveAsFlow()

    fun setState(reduce: State.() -> State) {
        _state.value = currentState.reduce()
    }

    open fun setIntent(intent: Intent) {
        dispatchIntent(intent)
    }

    private fun dispatchIntent(intent: Intent) = viewModelScope.launch {
        handleEvent(intent)
    }

    protected abstract suspend fun handleEvent(intent: Intent)

    fun setSideEffect(sideEffect: () -> SideEffect) {
        viewModelScope.launch { _sideEffect.send(sideEffect()) }
    }
}
