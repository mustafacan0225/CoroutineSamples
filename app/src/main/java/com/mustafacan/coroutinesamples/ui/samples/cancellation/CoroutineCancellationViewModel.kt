package com.mustafacan.coroutinesamples.ui.samples.cancellation

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
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineCancellationViewModel :
    BaseViewModel<AnimalsUiStateManager.AnimalsScreenState, AnimalsUiStateManager.AnimalsScreenEvent,
            AnimalsUiStateManager.AnimalsScreenEffect>(
        initialState = AnimalsUiStateManager.AnimalsScreenState.initial(),
        uiStateManager = AnimalsUiStateManager()
    ) {

    init {
        Log.d("initVM", "CoroutineCancellationViewModel")
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

    fun cancelJobSample() {
        viewModelScope.launch {
            launch {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
                val list = MockRepository.getDogs()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
            }

            val jobCats = launch {
                try {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
                    val list = MockRepository.getCats()
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(list))
                } catch (e: CancellationException) {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorCats(errorMessage = e.message.toString() + "\n----------\nCancellationException caught with \"try catch\""))
                }

            }

            delay(1500)
            jobCats.cancel()

        }

    }

    fun cancelParentJobSample() {
        viewModelScope.launch {
            launch {
                try {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
                    val list = MockRepository.getDogs()
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
                } catch (e: CancellationException) {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorDogs(errorMessage = e.message.toString() + "\n----------\nCancellationException caught with \"try catch\""))
                }

            }

            launch {
                try {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
                    val list = MockRepository.getCats()
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(list))
                } catch (e: CancellationException) {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorCats(errorMessage = e.message.toString() + "\n----------\nCancellationException caught with \"try catch\""))
                }
            }

            delay(1500)
            this.cancel()
        }
    }

    fun withContextNonCancellable() {
        viewModelScope.launch {
            val jobDogs = launch {
                try {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
                    val list = MockRepository.getDogs()
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
                } catch (e: CancellationException) {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorDogs(e.message.toString(), isAppendable = true))
                } finally {
                    withContext(NonCancellable) {
                        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorDogs("Called suspandable function in withContext(NonCancellable)", isAppendable = true))
                        clearData()
                    }
                }

            }


            delay(1500)
            jobDogs.cancel()

        }
    }

    suspend fun clearData() {
        delay(1000)
        sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorDogs("Completed suspandable function.", isAppendable = true))

    }

    suspend fun invokeOnCompletion() {
        viewModelScope.launch(exceptionHandler) {

            launch {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingDogs)
                val list = MockRepository.getDogsWithError()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedDogs(list))
            }.invokeOnCompletion {
                if (it == null) {
                    Log.d("coroutine:", "completed dogs without error")
                } else if (it is CancellationException) {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorDogs(errorMessage = it.message.toString() + "\n----------\nCancellationException caught with \"invokeOnCompletion\""))
                } else {
                    Log.d("coroutine:", "otherwise error: " + it.message.toString())
                }
            }

            launch {
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.LoadingCats)
                val list = MockRepository.getCats()
                sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.CompletedCats(list))
            }.invokeOnCompletion {
                if (it == null) {
                    Log.d("coroutine:", "completed cats without error")
                } else if (it is CancellationException) {
                    sendEvent(AnimalsUiStateManager.AnimalsScreenEvent.ErrorCats(errorMessage = it.message.toString() + "\n----------\nCancellationException caught with \"invokeOnCompletion\""))
                } else {
                    Log.d("coroutine:", "otherwise error: " + it.message.toString())
                }
            }

            delay(1500)
            this.cancel()

        }

    }

}