package com.jm.myscreenshots.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

private const val MVI_TAG = "MVI: "

abstract class BaseProcessor<Event : BaseEvent, State : BaseState, Effect : BaseEffect> :
    ViewModel() {

    private val initialState: State by lazy { createInitialState() }

    abstract fun createInitialState(): State
    abstract fun handleEvent(event: Event)

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _eventsFlow: MutableSharedFlow<Event> = MutableSharedFlow()
    val events = _eventsFlow.asSharedFlow()

    private val _effectsFlow: Channel<Effect> = Channel()
    val effects = _effectsFlow.receiveAsFlow()

    init { subscribeToEvents() }

    val currentState: State
        get() = _uiState.value

    fun setState(state: State) {
        Timber.i("$MVI_TAG setting new state $state")
        _uiState.value = state
    }

    fun emitEvent(event: Event) = viewModelScope.launch {
        Timber.i("$MVI_TAG emitting event $event")
        _eventsFlow.emit(event)
    }

    fun emitEffect(effect: Effect) = viewModelScope.launch {
        Timber.i("$MVI_TAG emitting effect $effect")
        _effectsFlow.send(effect)
    }

    private fun subscribeToEvents() = viewModelScope.launch {
        Timber.i("$MVI_TAG successfully subscribed for events")
        events.collect { handleEvent(it) }
    }
}

abstract class BaseEvent

abstract class BaseState

abstract class BaseEffect
