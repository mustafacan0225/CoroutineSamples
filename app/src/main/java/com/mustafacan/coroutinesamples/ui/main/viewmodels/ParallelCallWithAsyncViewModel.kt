package com.mustafacan.coroutinesamples.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import com.mustafacan.coroutinesamples.ui.data.MockRepository.getBirds
import com.mustafacan.coroutinesamples.ui.data.MockRepository.getCats
import com.mustafacan.coroutinesamples.ui.data.MockRepository.getDogs
import com.mustafacan.coroutinesamples.ui.main.MainScreenUiStateManager
import com.mustafacan.coroutinesamples.ui.model.AnimalsUiModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ParallelCallWithAsyncViewModel :
    BaseViewModel<MainScreenUiStateManager.MainScreenState, MainScreenUiStateManager.MainScreenEvent,
            MainScreenUiStateManager.MainScreenEffect>(
        initialState = MainScreenUiStateManager.MainScreenState.initial(),
        uiStateManager = MainScreenUiStateManager()
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
        sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingStarted)
        sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingDogs)
        sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingCats)
        sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingBirds)
    }

    fun completedFetchData(animals: AnimalsUiModel) {
        sendEvent(MainScreenUiStateManager.MainScreenEvent.CompletedDogs(animals.dogs))
        sendEvent(MainScreenUiStateManager.MainScreenEvent.CompletedCats(animals.cats))
        sendEvent(MainScreenUiStateManager.MainScreenEvent.CompletedBirds(animals.birds))
        sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingCompleted)
    }


}