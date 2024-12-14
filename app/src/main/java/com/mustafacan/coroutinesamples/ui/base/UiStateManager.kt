package com.mustafacan.coroutinesamples.ui.base

interface UiStateManager<State : UiStateManager.ViewState, Event : UiStateManager.ViewEvent, Effect : UiStateManager.ViewEffect> {
    interface ViewState

    interface ViewEvent

    interface ViewEffect

    fun handleEvent(previousState: State, event: Event): Pair<State, Effect?>
}