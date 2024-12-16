package com.mustafacan.coroutinesamples.ui.main.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import com.mustafacan.coroutinesamples.ui.main.DispatcherUiStateManager
import com.mustafacan.coroutinesamples.ui.model.DispatcherUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class DispatcherViewModel :
    BaseViewModel<DispatcherUiStateManager.DispatcherScreenState, DispatcherUiStateManager.DispatcherScreenEvent,
            DispatcherUiStateManager.DispatcherScreenEffect>(
        initialState = DispatcherUiStateManager.DispatcherScreenState.initial(),
        uiStateManager = DispatcherUiStateManager()
    ) {

    suspend fun dispatcherSamples() {
        viewModelScope.launch {
            Log.d("coroutine", "viewModelScope: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "viewModelScope", threadName = "${Thread.currentThread().name}")))
        }.join()

        CoroutineScope(Dispatchers.Main).launch {
            Log.d("coroutine", "Dispatchers.Main: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "Dispatchers.Main", threadName = "${Thread.currentThread().name}")))
        }.join()

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("coroutine", "Dispatchers.IO: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "Dispatchers.IO", threadName = "${Thread.currentThread().name}")))
        }.join()

        CoroutineScope(Dispatchers.Default).launch {
            Log.d("coroutine", "Dispatchers.Default: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "Dispatchers.Default", threadName = "${Thread.currentThread().name}")))
        }.join()

        CoroutineScope(Dispatchers.Unconfined).launch {
            Log.d("coroutine", "Dispatchers.Unconfined: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "Dispatchers.Unconfined", threadName = "${Thread.currentThread().name}")))
            delay(100)
            Log.d("coroutine", "Dispatchers.Unconfined: After suspension, Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "Dispatchers.Unconfined (after suspension)", threadName = "${Thread.currentThread().name}")))
        }.join()

        GlobalScope.launch {
            Log.d("coroutine", "GlobalScope: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "GlobalScope", threadName = "${Thread.currentThread().name}")))
        }.join()

        coroutineScope {
            Log.d("coroutine", "coroutineScope: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "coroutineScope", threadName = "${Thread.currentThread().name}")))
        }

        supervisorScope {
            Log.d("coroutine", "supervisorScope: Thread -> ${Thread.currentThread().name}")
            sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.AddDispatcherInfo(DispatcherUiModel(dispatcherName = "supervisorScope", threadName = "${Thread.currentThread().name}")))
        }
    }

    fun clearLog() {
        sendEvent(DispatcherUiStateManager.DispatcherScreenEvent.ClearDispatcherInfo)
    }

}