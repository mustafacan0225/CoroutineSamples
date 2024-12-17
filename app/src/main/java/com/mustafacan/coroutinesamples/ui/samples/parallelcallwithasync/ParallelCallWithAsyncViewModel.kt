package com.mustafacan.coroutinesamples.ui.samples.parallelcallwithasync

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import com.mustafacan.coroutinesamples.ui.data.MockRepository.getBirds
import com.mustafacan.coroutinesamples.ui.data.MockRepository.getCats
import com.mustafacan.coroutinesamples.ui.data.MockRepository.getDogs
import com.mustafacan.coroutinesamples.ui.samples.AnimalsUiStateManager
import com.mustafacan.coroutinesamples.ui.model.AnimalsUiModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ParallelCallWithAsyncViewModel :
    BaseViewModel<AnimalsUiStateManager.AnimalsScreenState, AnimalsUiStateManager.AnimalsScreenEvent,
            AnimalsUiStateManager.AnimalsScreenEffect>(
        initialState = AnimalsUiStateManager.AnimalsScreenState.initial(),
        uiStateManager = AnimalsUiStateManager()
    ) {

    init {
        Log.d("initVM", "ParallelCallWithAsyncViewModel")
    }

    suspend fun getAllAnimals() : AnimalsUiModel {
        return coroutineScope {
            val deferredDogs = async {
                getDogs()
            }

            val deferredCats = async {
                getCats()
            }

            val deferredBirds = async {
                getBirds()
            }

            val dogList = deferredDogs.await()
            val catList = deferredCats.await()
            val birdList = deferredBirds.await()

            return@coroutineScope AnimalsUiModel(dogList, catList, birdList)
        }

    }


    fun getAllData() {
        viewModelScope.launch {
            startLoading()
            val animals = getAllAnimals()
            completedFetchData(animals)
        }
    }

    fun startLoading() {
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingStarted)
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingBirds)
    }

    fun completedFetchData(animals: AnimalsUiModel) {
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(animals.dogs))
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(animals.cats))
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedBirds(animals.birds))
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCompleted)
    }


}