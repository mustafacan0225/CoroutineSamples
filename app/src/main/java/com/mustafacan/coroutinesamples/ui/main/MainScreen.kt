package com.mustafacan.coroutinesamples.ui.main

import androidx.compose.runtime.Composable
import com.mustafacan.coroutinesamples.ui.samples.dispatchers.DispatcherScreen
import com.mustafacan.coroutinesamples.ui.samples.parallelcallwithasync.ParallelCallWithAsyncScreen
import com.mustafacan.coroutinesamples.ui.samples.parallelcallwithjob.ParallelCallWithJobScreen

@Composable
fun MainScreen() {

    /******* Dispatcher Sample ********/
    DispatcherScreen()

    /******* Parallel Call With Job ********/
    //ParallelCallWithJobScreen()

    /******* Parallel Call With Async ********/
    //ParallelCallWithAsyncScreen()
}