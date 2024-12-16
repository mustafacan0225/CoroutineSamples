package com.mustafacan.coroutinesamples.ui.main

import androidx.compose.runtime.Immutable
import com.mustafacan.coroutinesamples.model.Bird
import com.mustafacan.coroutinesamples.model.Cat
import com.mustafacan.coroutinesamples.model.Dog
import com.mustafacan.coroutinesamples.ui.base.UiStateManager
import java.util.Calendar


class MainScreenUiStateManager: UiStateManager<MainScreenUiStateManager.MainScreenState, MainScreenUiStateManager.MainScreenEvent, MainScreenUiStateManager.MainScreenEffect> {
    @Immutable
    sealed class MainScreenEvent : UiStateManager.ViewEvent {
        object LoadingDogs : MainScreenEvent()
        object LoadingCats : MainScreenEvent()
        object LoadingBirds : MainScreenEvent()
        data class CompletedDogs(val dogList: List<Dog>) : MainScreenEvent()
        data class CompletedCats(val catList: List<Cat>) : MainScreenEvent()
        data class CompletedBirds(val birdList: List<Bird>) : MainScreenEvent()
        data class ErrorDogs(val errorMessage: String) : MainScreenEvent()
        data class ErrorCats(val errorMessage: String) : MainScreenEvent()
        data class ErrorBirds(val errorMessage: String) : MainScreenEvent()
        object LoadingStarted : MainScreenEvent()
        object LoadingCompleted : MainScreenEvent()
    }

    @Immutable
    data class MainScreenState(
        val loadingDogs: Boolean? = null,
        val loadingCats: Boolean? = null,
        val loadingBirds: Boolean? = null,
        val dogList: List<Dog>? = null,
        val catList: List<Cat>? = null,
        val birdList: List<Bird>? = null,
        val errorMessageForDogs: String? = null,
        val errorMessageForCats: String? = null,
        val errorMessageForBirds: String? = null,
        val loadingStartedTime: Long? = null,
        val completionInfo: String? = null
    ) : UiStateManager.ViewState {
        companion object {
            fun initial(): MainScreenState {
                return MainScreenState()
            }
        }
    }

    @Immutable
    sealed class MainScreenEffect : UiStateManager.ViewEffect {

    }

    override fun handleEvent(
        previousState: MainScreenState,
        event: MainScreenEvent
    ): Pair<MainScreenState, MainScreenEffect?> {

        return when (event) {

            is MainScreenEvent.LoadingDogs -> {
                previousState.copy(loadingDogs = true, dogList = null, errorMessageForDogs = null) to null
            }

            is MainScreenEvent.LoadingCats -> {
                previousState.copy(loadingCats = true, catList = null, errorMessageForCats = null) to null
            }

            is MainScreenEvent.LoadingBirds -> {
                previousState.copy(loadingBirds = true, birdList = null, errorMessageForBirds = null) to null
            }

            is MainScreenEvent.CompletedDogs -> {
                previousState.copy(loadingDogs = false, dogList = event.dogList, errorMessageForDogs = null) to null
            }

            is MainScreenEvent.CompletedCats -> {
                previousState.copy(loadingCats = false, catList = event.catList, errorMessageForCats = null) to null
            }

            is MainScreenEvent.CompletedBirds -> {
                previousState.copy(loadingBirds = false, birdList = event.birdList, errorMessageForBirds = null) to null
            }

            is MainScreenEvent.ErrorDogs -> {
                previousState.copy(loadingDogs = false, errorMessageForDogs = event.errorMessage, dogList = null) to null
            }

            is MainScreenEvent.ErrorCats -> {
                previousState.copy(loadingCats = false, errorMessageForCats = event.errorMessage, catList = null) to null
            }

            is MainScreenEvent.ErrorBirds -> {
                previousState.copy(loadingBirds = false, errorMessageForBirds = event.errorMessage, birdList = null) to null
            }

            is MainScreenEvent.LoadingStarted -> {
                previousState.copy(loadingStartedTime = Calendar.getInstance().timeInMillis, completionInfo = null) to null
            }

            is MainScreenEvent.LoadingCompleted -> {
                val completionTime = (Calendar.getInstance().timeInMillis - previousState.loadingStartedTime!!) / 1000
                val completionInfo = "$completionTime seconds"
                previousState.copy(loadingStartedTime = Calendar.getInstance().timeInMillis, completionInfo = completionInfo) to null
            }

        }
    }
}