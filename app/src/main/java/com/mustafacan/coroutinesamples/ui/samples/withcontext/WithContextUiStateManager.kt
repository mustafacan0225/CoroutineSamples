package com.mustafacan.coroutinesamples.ui.samples.withcontext

import androidx.compose.runtime.Immutable
import com.mustafacan.coroutinesamples.model.Cat
import com.mustafacan.coroutinesamples.model.Dog
import com.mustafacan.coroutinesamples.ui.base.UiStateManager
import java.util.Calendar

class WithContextUiStateManager :
    UiStateManager<WithContextUiStateManager.WithContextScreenState, WithContextUiStateManager.WithContextScreenEvent, WithContextUiStateManager.WithContextScreenEffect> {
    @Immutable
    sealed class WithContextScreenEvent : UiStateManager.ViewEvent {
        object LoadingDogs : WithContextScreenEvent()
        object LoadingCats : WithContextScreenEvent()
        data class CompletedDogs(val dogList: List<Dog>) : WithContextScreenEvent()
        data class CompletedCats(val catList: List<Cat>) : WithContextScreenEvent()
        data class ErrorDogs(val errorMessage: String) : WithContextScreenEvent()
        data class ErrorCats(val errorMessage: String) : WithContextScreenEvent()
        object LoadingStarted : WithContextScreenEvent()
        object LoadingCompleted : WithContextScreenEvent()
        data class BeforeWithContext(val info: String) : WithContextScreenEvent()
        data class AfterWithContext(val info: String) : WithContextScreenEvent()
    }

    @Immutable
    data class WithContextScreenState(
        val loadingDogs: Boolean? = null,
        val loadingCats: Boolean? = null,
        val dogList: List<Dog>? = null,
        val catList: List<Cat>? = null,
        val errorMessageForDogs: String? = null,
        val errorMessageForCats: String? = null,
        val loadingStartedTime: Long? = null,
        val completionInfo: String? = null,
        val beforeWithContext: String? = null,
        val afterWithContext: String? = null,
    ) : UiStateManager.ViewState {
        companion object {
            fun initial(): WithContextScreenState {
                return WithContextScreenState()
            }
        }
    }

    override fun handleEvent(
        previousState: WithContextScreenState,
        event: WithContextScreenEvent
    ): Pair<WithContextScreenState, WithContextScreenEffect?> {

        return when (event) {

            is WithContextScreenEvent.LoadingDogs -> {
                previousState.copy(
                    loadingDogs = true,
                    dogList = null,
                    errorMessageForDogs = null
                ) to null
            }

            is WithContextScreenEvent.LoadingCats -> {
                previousState.copy(
                    loadingCats = true,
                    catList = null,
                    errorMessageForCats = null,
                ) to null
            }

            is WithContextScreenEvent.CompletedDogs -> {
                previousState.copy(
                    loadingDogs = false,
                    dogList = event.dogList,
                    errorMessageForDogs = null
                ) to null
            }

            is WithContextScreenEvent.CompletedCats -> {
                previousState.copy(
                    loadingCats = false,
                    catList = event.catList,
                    errorMessageForCats = null
                ) to null
            }

            is WithContextScreenEvent.ErrorDogs -> {
                previousState.copy(
                    loadingDogs = false,
                    errorMessageForDogs = event.errorMessage,
                    dogList = null
                ) to null
            }

            is WithContextScreenEvent.ErrorCats -> {
                previousState.copy(
                    loadingCats = false,
                    errorMessageForCats = event.errorMessage,
                    catList = null
                ) to null
            }

            is WithContextScreenEvent.LoadingStarted -> {
                previousState.copy(
                    loadingStartedTime = Calendar.getInstance().timeInMillis,
                    completionInfo = null,
                    beforeWithContext = null,
                    afterWithContext = null
                ) to null
            }

            is WithContextScreenEvent.LoadingCompleted -> {
                val completionTime =
                    (Calendar.getInstance().timeInMillis - previousState.loadingStartedTime!!) / 1000
                val completionInfo = "$completionTime seconds"
                previousState.copy(
                    loadingStartedTime = Calendar.getInstance().timeInMillis,
                    completionInfo = completionInfo
                ) to null
            }

            is WithContextScreenEvent.AfterWithContext -> {
                previousState.copy(afterWithContext = event.info) to null
            }

            is WithContextScreenEvent.BeforeWithContext -> {
                previousState.copy(beforeWithContext = event.info) to null
            }

        }
    }

    @Immutable
    sealed class WithContextScreenEffect : UiStateManager.ViewEffect {

    }
}