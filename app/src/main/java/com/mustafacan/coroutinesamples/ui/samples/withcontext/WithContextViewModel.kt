package com.mustafacan.coroutinesamples.ui.samples.withcontext

import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import com.mustafacan.coroutinesamples.ui.data.MockRepository
import com.mustafacan.coroutinesamples.ui.model.AnimalsUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WithContextViewModel : BaseViewModel<WithContextUiStateManager.WithContextScreenState, WithContextUiStateManager.WithContextScreenEvent,
        WithContextUiStateManager.WithContextScreenEffect>(
    initialState = WithContextUiStateManager.WithContextScreenState.initial(),
    uiStateManager = WithContextUiStateManager()
) {

    suspend fun singleWithContext() {
        viewModelScope.launch {

            loading()

            val animals = withContext(Dispatchers.IO) {
                sendEvent(WithContextUiStateManager.WithContextScreenEvent.AfterWithContext(info = "works on ${Thread.currentThread().name} thread"))
                val deferredDogs = async { MockRepository.getDogs() }
                val deferredCats = async { MockRepository.getCats() }
                val dogList = deferredDogs.await()
                val catList = deferredCats.await()
                AnimalsUiModel(dogs = dogList, cats = catList, birds = listOf())
            }

            completed(animals)

        }

    }

    //multiple withContext, works sequentially
    suspend fun multipleWithContext() {

        viewModelScope.launch {

            sendEvent(WithContextUiStateManager.WithContextScreenEvent.LoadingStarted)
            sendEvent(WithContextUiStateManager.WithContextScreenEvent.LoadingDogs)
            sendEvent(WithContextUiStateManager.WithContextScreenEvent.BeforeWithContext(info = "works on ${Thread.currentThread().name} thread"))

            val dogList = withContext(Dispatchers.IO) {
                sendEvent(WithContextUiStateManager.WithContextScreenEvent.AfterWithContext(info = "works on ${Thread.currentThread().name} thread"))
                MockRepository.getDogs()
            }

            sendEvent(WithContextUiStateManager.WithContextScreenEvent.CompletedDogs(dogList))
            sendEvent(WithContextUiStateManager.WithContextScreenEvent.LoadingCats)

            val catList = withContext(Dispatchers.IO) {
                MockRepository.getCats()
            }

            sendEvent(WithContextUiStateManager.WithContextScreenEvent.CompletedCats(catList))
            sendEvent(WithContextUiStateManager.WithContextScreenEvent.LoadingCompleted)
        }

    }

    fun loading() {
        //update ui
        sendEvent(WithContextUiStateManager.WithContextScreenEvent.LoadingStarted)
        sendEvent(WithContextUiStateManager.WithContextScreenEvent.LoadingDogs)
        sendEvent(WithContextUiStateManager.WithContextScreenEvent.LoadingCats)
        sendEvent(WithContextUiStateManager.WithContextScreenEvent.BeforeWithContext(info = "works on ${Thread.currentThread().name} thread"))
    }

    fun completed(animals: AnimalsUiModel) {
        //update ui
        sendEvent(WithContextUiStateManager.WithContextScreenEvent.CompletedDogs(animals.dogs))
        sendEvent(WithContextUiStateManager.WithContextScreenEvent.CompletedCats(animals.cats))
        sendEvent(WithContextUiStateManager.WithContextScreenEvent.LoadingCompleted)
    }


}