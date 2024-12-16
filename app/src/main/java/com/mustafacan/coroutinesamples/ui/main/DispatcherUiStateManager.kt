package com.mustafacan.coroutinesamples.ui.main

import androidx.compose.runtime.Immutable
import com.mustafacan.coroutinesamples.ui.base.UiStateManager
import com.mustafacan.coroutinesamples.ui.model.DispatcherUiModel
import java.util.Calendar

class DispatcherUiStateManager: UiStateManager<DispatcherUiStateManager.DispatcherScreenState, DispatcherUiStateManager.DispatcherScreenEvent, DispatcherUiStateManager.DispatcherScreenEffect> {
    @Immutable
    sealed class DispatcherScreenEvent : UiStateManager.ViewEvent {
        data class AddDispatcherInfo(val dispatcherInfo: DispatcherUiModel) : DispatcherScreenEvent()
        object ClearDispatcherInfo : DispatcherScreenEvent()
    }

    @Immutable
    data class DispatcherScreenState(val dispatcherInfoList: List<DispatcherUiModel>) : UiStateManager.ViewState {
        companion object {
            fun initial(): DispatcherScreenState {
                return DispatcherScreenState(dispatcherInfoList = listOf())
            }
        }
    }

    override fun handleEvent(
        previousState: DispatcherScreenState,
        event: DispatcherScreenEvent
    ): Pair<DispatcherScreenState, DispatcherScreenEffect?> {

        return when (event) {

            is DispatcherScreenEvent.AddDispatcherInfo -> {
                previousState.copy(dispatcherInfoList = previousState.dispatcherInfoList.plus(event.dispatcherInfo)) to null
            }

            is DispatcherScreenEvent.ClearDispatcherInfo -> {
                previousState.copy(dispatcherInfoList = listOf()) to null
            }

        }
    }

    @Immutable
    sealed class DispatcherScreenEffect : UiStateManager.ViewEffect {

    }
}