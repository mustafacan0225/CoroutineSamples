package com.mustafacan.coroutinesamples.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import com.mustafacan.coroutinesamples.ui.main.DispatcherUiStateManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DispatcherViewModel :
    BaseViewModel<DispatcherUiStateManager.DispatcherScreenState, DispatcherUiStateManager.DispatcherScreenEvent,
            DispatcherUiStateManager.DispatcherScreenEffect>(
        initialState = DispatcherUiStateManager.DispatcherScreenState.initial(),
        uiStateManager = DispatcherUiStateManager()
    ) {

    suspend fun dispatcherSamples() {
        viewModelScope.launch {
            Log.d("coroutine", "viewModelScope: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.PrintLog("viewModelScope\nThread -> ${Thread.currentThread().name}\n\n"))
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.d("coroutine", "Dispatchers.Main: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.PrintLog("Dispatchers.Main\nThread -> ${Thread.currentThread().name}\n\n"))
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("coroutine", "Dispatchers.IO: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.PrintLog("Dispatchers.IO\nThread -> ${Thread.currentThread().name}\n\n"))
        }

        CoroutineScope(Dispatchers.Default).launch {
            Log.d("coroutine", "Dispatchers.Default: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.PrintLog("Dispatchers.Default\nThread -> ${Thread.currentThread().name}\n\n"))
        }

        CoroutineScope(Dispatchers.Unconfined).launch {
            Log.d("coroutine", "Dispatchers.Unconfined: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.PrintLog("Dispatchers.Unconfined\nThread -> ${Thread.currentThread().name}\n\n"))
            delay(100)

            Log.d("coroutine", "Dispatchers.Unconfined: After suspension, Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.PrintLog("Dispatchers.Unconfined after suspension\nThread -> ${Thread.currentThread().name}\n\n"))
        }

        GlobalScope.launch {
            Log.d("coroutine", "GlobalScope: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.PrintLog("GlobalScope\nThread -> ${Thread.currentThread().name}\n\n"))
        }
    }

    fun clearLog() {
        sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.ClearLog)
    }

}