package com.mustafacan.coroutinesamples.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import com.mustafacan.coroutinesamples.ui.data.MockRepository
import com.mustafacan.coroutinesamples.ui.main.MainScreenUiStateManager
import kotlinx.coroutines.launch

class ParallelCallWithJobViewModel :
    BaseViewModel<MainScreenUiStateManager.MainScreenState, MainScreenUiStateManager.MainScreenEvent,
            MainScreenUiStateManager.MainScreenEffect>(
        initialState = MainScreenUiStateManager.MainScreenState.initial(),
        uiStateManager = MainScreenUiStateManager()
    ) {

    init {
        Log.d("initVM", "ParallelCallWithJobViewModel")
    }

    fun getAllData() {

        viewModelScope.launch {
            sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingStarted)
            val jobDogs = launch {
                Log.d("coroutine:", "started dogs")
                sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingDogs)
                val list = MockRepository.getDogs()
                sendEvent(MainScreenUiStateManager.MainScreenEvent.CompletedDogs(list))
                Log.d("coroutine:", "completed dogs")
            }

            val jobCats = launch {
                Log.d("coroutine:", "started cats")
                sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingCats)
                val list = MockRepository.getCats()
                sendEvent(MainScreenUiStateManager.MainScreenEvent.CompletedCats(list))
                Log.d("coroutine:", "completed cats")
            }

            val jobBirds = launch {
                Log.d("coroutine:", "started birds")
                sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingBirds)
                val list = MockRepository.getBirds()
                sendEvent(MainScreenUiStateManager.MainScreenEvent.CompletedBirds(list))
                Log.d("coroutine:", "completed birds")
            }

            Log.d("coroutine:", "jobDogs.isActive ${jobDogs.isActive}, jobDogs.isCompleted ${jobDogs.isCompleted}")
            Log.d("coroutine:", "jobCats.isActive ${jobCats.isActive}, jobCats.isCompleted ${jobCats.isCompleted}")
            Log.d("coroutine:", "jobBirds.isActive ${jobBirds.isActive}, jobBirds.isCompleted ${jobBirds.isCompleted}")

            jobDogs.join()
            jobCats.join()
            jobBirds.join()

            sendEvent(MainScreenUiStateManager.MainScreenEvent.LoadingCompleted)
            Log.d("coroutine:", "data fetch completed")
            Log.d("coroutine:", "jobDogs.isActive ${jobDogs.isActive}, jobDogs.isCompleted ${jobDogs.isCompleted}")
            Log.d("coroutine:", "jobCats.isActive ${jobCats.isActive}, jobCats.isCompleted ${jobCats.isCompleted}")
            Log.d("coroutine:", "jobBirds.isActive ${jobBirds.isActive}, jobBirds.isCompleted ${jobBirds.isCompleted}")

        }
    }
}