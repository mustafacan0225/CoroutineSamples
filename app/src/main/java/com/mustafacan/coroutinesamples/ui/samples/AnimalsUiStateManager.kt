package com.mustafacan.coroutinesamples.ui.samples

import androidx.compose.runtime.Immutable
import com.mustafacan.coroutinesamples.model.Bird
import com.mustafacan.coroutinesamples.model.Cat
import com.mustafacan.coroutinesamples.model.Dog
import com.mustafacan.coroutinesamples.ui.base.UiStateManager
import java.util.Calendar


class AnimalsUiStateManager: UiStateManager<AnimalsUiStateManager.AnimalsScreenState, AnimalsUiStateManager.AnimalsScreenEvent, AnimalsUiStateManager.AnimalsScreenEffect> {
    @Immutable
    sealed class AnimalsScreenEvent : UiStateManager.ViewEvent {
        object LoadingDogs : AnimalsScreenEvent()
        object LoadingCats : AnimalsScreenEvent()
        object LoadingBirds : AnimalsScreenEvent()
        data class CompletedDogs(val dogList: List<Dog>) : AnimalsScreenEvent()
        data class CompletedCats(val catList: List<Cat>) : AnimalsScreenEvent()
        data class CompletedBirds(val birdList: List<Bird>) : AnimalsScreenEvent()
        data class ErrorDogs(val errorMessage: String) : AnimalsScreenEvent()
        data class ErrorCats(val errorMessage: String) : AnimalsScreenEvent()
        data class ErrorBirds(val errorMessage: String) : AnimalsScreenEvent()
        object LoadingStarted : AnimalsScreenEvent()
        object LoadingCompleted : AnimalsScreenEvent()
    }

    @Immutable
    data class AnimalsScreenState(
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
            fun initial(): AnimalsScreenState {
                return AnimalsScreenState()
            }
        }
    }

    @Immutable
    sealed class AnimalsScreenEffect : UiStateManager.ViewEffect {

    }

    override fun handleEvent(
        previousState: AnimalsScreenState,
        event: AnimalsScreenEvent
    ): Pair<AnimalsScreenState, AnimalsScreenEffect?> {

        return when (event) {

            is AnimalsScreenEvent.LoadingDogs -> {
                previousState.copy(loadingDogs = true, dogList = null, errorMessageForDogs = null) to null
            }

            is AnimalsScreenEvent.LoadingCats -> {
                previousState.copy(loadingCats = true, catList = null, errorMessageForCats = null) to null
            }

            is AnimalsScreenEvent.LoadingBirds -> {
                previousState.copy(loadingBirds = true, birdList = null, errorMessageForBirds = null) to null
            }

            is AnimalsScreenEvent.CompletedDogs -> {
                previousState.copy(loadingDogs = false, dogList = event.dogList, errorMessageForDogs = null) to null
            }

            is AnimalsScreenEvent.CompletedCats -> {
                previousState.copy(loadingCats = false, catList = event.catList, errorMessageForCats = null) to null
            }

            is AnimalsScreenEvent.CompletedBirds -> {
                previousState.copy(loadingBirds = false, birdList = event.birdList, errorMessageForBirds = null) to null
            }

            is AnimalsScreenEvent.ErrorDogs -> {
                previousState.copy(loadingDogs = false, errorMessageForDogs = event.errorMessage, dogList = null) to null
            }

            is AnimalsScreenEvent.ErrorCats -> {
                previousState.copy(loadingCats = false, errorMessageForCats = event.errorMessage, catList = null) to null
            }

            is AnimalsScreenEvent.ErrorBirds -> {
                previousState.copy(loadingBirds = false, errorMessageForBirds = event.errorMessage, birdList = null) to null
            }

            is AnimalsScreenEvent.LoadingStarted -> {
                previousState.copy(loadingStartedTime = Calendar.getInstance().timeInMillis, completionInfo = null) to null
            }

            is AnimalsScreenEvent.LoadingCompleted -> {
                val completionTime = (Calendar.getInstance().timeInMillis - previousState.loadingStartedTime!!) / 1000
                val completionInfo = "$completionTime seconds"
                previousState.copy(loadingStartedTime = Calendar.getInstance().timeInMillis, completionInfo = completionInfo) to null
            }

        }
    }
}