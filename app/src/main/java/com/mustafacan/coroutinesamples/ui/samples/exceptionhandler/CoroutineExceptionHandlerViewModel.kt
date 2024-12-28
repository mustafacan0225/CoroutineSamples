package com.mustafacan.coroutinesamples.ui.samples.exceptionhandler

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import com.mustafacan.coroutinesamples.ui.data.MockRepository
import com.mustafacan.coroutinesamples.ui.samples.AnimalsUiStateManager
import com.mustafacan.coroutinesamples.ui.model.CustomExceptionBirds
import com.mustafacan.coroutinesamples.ui.model.CustomExceptionCats
import com.mustafacan.coroutinesamples.ui.model.CustomExceptionDogs
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class CoroutineExceptionHandlerViewModel :
    BaseViewModel<AnimalsUiStateManager.AnimalsScreenState, AnimalsUiStateManager.AnimalsScreenEvent,
            AnimalsUiStateManager.AnimalsScreenEffect>(
        initialState = AnimalsUiStateManager.AnimalsScreenState.initial(),
        uiStateManager = AnimalsUiStateManager()
    ) {

    init {
        Log.d("initVM", "CoroutineExceptionHandlerViewModel")
    }

    val exceptionHandler = CoroutineExceptionHandler { _, e ->
        Log.d("coroutine:", "exceptionHandler: ${e.message}" )
        when(e) {
            is CustomExceptionDogs -> {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorDogs(e.message?: "An error occurred"))
            }

            is CustomExceptionBirds -> {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorBirds(e.message?: "An error occurred"))
            }

            is CustomExceptionCats -> {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorCats(e.message?: "An error occurred"))
            }

        }
    }

    suspend fun exceptionHandlerWithSupervisorJob() {
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingStarted)
        val scope = CoroutineScope(SupervisorJob() + exceptionHandler)
        scope.launch {
            sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
            val list = MockRepository.getDogs()
            sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
        }

        scope.launch {
            sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
            val list = MockRepository.getCats()
            sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(list))
        }

        scope.launch {
            sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingBirds)
            val list = MockRepository.getBirdsWithError()
            sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedBirds(list))
        }

    }

    suspend fun exceptionHandlerWithJob() {
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingStarted)

        val scope = CoroutineScope(Job() + exceptionHandler)
        val jobDogs = scope.launch {
            try {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
                val list = MockRepository.getDogs()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
            } catch (e: CancellationException) {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorDogs("This job has been cancelled."))
            }

        }

        val jobCats = scope.launch {
            try {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
                val list = MockRepository.getCats()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(list))
            } catch (e: CancellationException) {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorCats("This job has been cancelled."))
            }

        }

        val jobBirds = scope.launch {
            try {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingBirds)
                val list = MockRepository.getBirdsWithError()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedBirds(list))
            } catch (e: CancellationException) {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorBirds("This job has been cancelled."))
            }
        }

        jobDogs.join()
        jobCats.join()
        jobBirds.join()

        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCompleted)
        Log.d("coroutine:", "jobDogs.isActive ${jobDogs.isActive}, jobDogs.isCompleted ${jobDogs.isCompleted}")
        Log.d("coroutine:", "jobCats.isActive ${jobCats.isActive}, jobCats.isCompleted ${jobCats.isCompleted}")
        Log.d("coroutine:", "jobBirds.isActive ${jobBirds.isActive}, jobBirds.isCompleted ${jobBirds.isCompleted}")

    }

    suspend fun exceptionHandlerWithSupervisorScope() {
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingStarted)
        viewModelScope.launch(exceptionHandler) {
            supervisorScope {
                launch {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
                    val list = MockRepository.getDogs()
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
                }

                launch {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
                    val list = MockRepository.getCats()
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(list))
                }

                launch {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingBirds)
                    val list = MockRepository.getBirdsWithError()
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedBirds(list))
                }

            }
        }
    }

    suspend fun exceptionHandlerWithCoroutineScope() {
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingStarted)
        val parentJob = viewModelScope.launch(exceptionHandler) {
            coroutineScope {
                launch {
                    try {
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
                        val list = MockRepository.getDogs()
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
                    } catch (e: CancellationException) {
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorDogs("This job has been cancelled."))
                    }
                }

                launch {
                    try {
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
                        val list = MockRepository.getCats()
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(list))
                    } catch (e: CancellationException) {
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorCats("This job has been cancelled."))
                    }
                }

                launch {
                    try {
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingBirds)
                        val list = MockRepository.getBirdsWithError()
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedBirds(list))
                    } catch (e: CancellationException) {
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorBirds("This job has been cancelled."))
                    }
                }
            }
        }

        parentJob.join()
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCompleted)
    }

}