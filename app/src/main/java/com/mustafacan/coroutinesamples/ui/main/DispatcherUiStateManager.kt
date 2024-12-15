package com.mustafacan.coroutinesamples.ui.main

import androidx.compose.runtime.Immutable
import com.mustafacan.coroutinesamples.ui.base.UiStateManager

class DispatcherUiStateManager: UiStateManager<DispatcherUiStateManager.DispatcherScreenState, DispatcherUiStateManager.DispatcherScreenEvent, DispatcherUiStateManager.DispatcherScreenEffect> {
    @Immutable
    sealed class DispatcherScreenEvent : UiStateManager.ViewEvent {
        data class PrintLog(val log: String) : DispatcherScreenEvent()
        object ClearLog : DispatcherScreenEvent()
    }

    @Immutable
    data class DispatcherScreenState(
        val log: String? = null
    ) : UiStateManager.ViewState {
        companion object {
            fun initial(): DispatcherScreenState {
                return DispatcherScreenState()
            }
        }
    }

    override fun handleEvent(
        previousState: DispatcherScreenState,
        event: DispatcherScreenEvent
    ): Pair<DispatcherScreenState, DispatcherScreenEffect?> {

        return when (event) {

            is DispatcherScreenEvent.PrintLog -> {
                previousState.copy(log = if (previousState.log == null) event.log else previousState.log + event.log) to null
            }

            is DispatcherScreenEvent.ClearLog -> {
                previousState.copy(log = null) to null
            }

        }
    }

    @Immutable
    sealed class DispatcherScreenEffect : UiStateManager.ViewEffect {

    }
}