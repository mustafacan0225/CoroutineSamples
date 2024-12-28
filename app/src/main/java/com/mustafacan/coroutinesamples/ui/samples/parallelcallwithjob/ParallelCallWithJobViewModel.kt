package com.mustafacan.coroutinesamples.ui.samples.parallelcallwithjob

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import com.mustafacan.coroutinesamples.ui.data.MockRepository
import com.mustafacan.coroutinesamples.ui.samples.AnimalsUiStateManager
import kotlinx.coroutines.launch

class ParallelCallWithJobViewModel :
    BaseViewModel<AnimalsUiStateManager.AnimalsScreenState, AnimalsUiStateManager.AnimalsScreenEvent,
            AnimalsUiStateManager.AnimalsScreenEffect>(
        initialState = AnimalsUiStateManager.AnimalsScreenState.initial(),
        uiStateManager = AnimalsUiStateManager()
    ) {

    init {
        Log.d("initVM", "ParallelCallWithJobViewModel")
    }

    fun getAllData() {

        viewModelScope.launch {
            sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingStarted)
            val jobDogs = launch {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
                val list = MockRepository.getDogs()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
            }

            val jobCats = launch {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
                val list = MockRepository.getCats()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(list))
            }

            val jobBirds = launch {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingBirds)
                val list = MockRepository.getBirds()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedBirds(list))
            }

            jobDogs.join()
            jobCats.join()
            jobBirds.join()

            sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCompleted)
            Log.d("coroutine:", "data fetch completed")
            Log.d("coroutine:", "jobDogs.isActive ${jobDogs.isActive}, jobDogs.isCompleted ${jobDogs.isCompleted}")
            Log.d("coroutine:", "jobCats.isActive ${jobCats.isActive}, jobCats.isCompleted ${jobCats.isCompleted}")
            Log.d("coroutine:", "jobBirds.isActive ${jobBirds.isActive}, jobBirds.isCompleted ${jobBirds.isCompleted}")

        }
    }
}