package com.mustafacan.coroutinesamples.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.mustafacan.coroutinesamples.ui.main.screen.DispatcherScreen
import com.mustafacan.coroutinesamples.ui.main.screen.ParallelCallWithAsyncScreen
import com.mustafacan.coroutinesamples.ui.main.screen.ParallelCallWithJobScreen
import com.mustafacan.coroutinesamples.ui.main.viewmodels.ParallelCallWithJobViewModel

@Composable
fun MainScreen() {

    /******* Dispatcher Sample ********/
    //DispatcherScreen()

    /******* Parallel Call With Job ********/
    //ParallelCallWithJobScreen()

    /******* Parallel Call With Async ********/
    ParallelCallWithAsyncScreen()
}