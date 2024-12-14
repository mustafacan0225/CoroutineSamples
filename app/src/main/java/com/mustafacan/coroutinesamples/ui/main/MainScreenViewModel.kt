package com.mustafacan.coroutinesamples.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mustafacan.coroutinesamples.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel :
    BaseViewModel<MainScreenUiStateManager.MainScreenState, MainScreenUiStateManager.MainScreenEvent,
            MainScreenUiStateManager.MainScreenEffect>(
        initialState = MainScreenUiStateManager.MainScreenState.initial(),
        uiStateManager = MainScreenUiStateManager()
    ) {

    suspend fun dispatcherSamples() {
        viewModelScope.launch {
            Log.d("coroutine", "viewModelScope: Thread -> ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Main).launch {
            Log.d("coroutine", "Dispatchers.Main: Thread -> ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("coroutine", "Dispatchers.IO: Thread -> ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Default).launch {
            Log.d("coroutine", "Dispatchers.Default: Thread -> ${Thread.currentThread().name}")
        }

        CoroutineScope(Dispatchers.Unconfined).launch {
            Log.d("coroutine", "Dispatchers.Unconfined: Thread -> ${Thread.currentThread().name}")
            delay(2000)
            Log.d("coroutine", "Dispatchers.Unconfined: After suspension, Thread -> ${Thread.currentThread().name}")
        }

        GlobalScope.launch {
            Log.d("coroutine", "GlobalScope: Thread -> ${Thread.currentThread().name}")
        }
    }

}