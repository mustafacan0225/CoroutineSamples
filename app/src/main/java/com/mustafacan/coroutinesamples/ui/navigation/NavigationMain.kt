package com.mustafacan.coroutinesamples.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mustafacan.coroutinesamples.ui.samplelist.SampleListScreen
import com.mustafacan.coroutinesamples.ui.samples.cancellation.CoroutineCancellationScreen
import com.mustafacan.coroutinesamples.ui.samples.dispatchers.DispatcherScreen
import com.mustafacan.coroutinesamples.ui.samples.exceptionhandler.CoroutineExceptionHandlerScreen
import com.mustafacan.coroutinesamples.ui.samples.parallelcallwithasync.ParallelCallWithAsyncScreen
import com.mustafacan.coroutinesamples.ui.samples.parallelcallwithjob.ParallelCallWithJobScreen
import com.mustafacan.coroutinesamples.ui.samples.withcontext.MultipleWithContextScreen
import com.mustafacan.coroutinesamples.ui.samples.withcontext.WithContextScreen

@Composable
fun NavigationMain() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestinationItem.SampleListScreen
    ) {
        composable<NavDestinationItem.SampleListScreen> {
            SampleListScreen(navController = navController)
        }

        composable<NavDestinationItem.DispatcherSample> {
            DispatcherScreen()
        }

        composable<NavDestinationItem.ParallelCallWithJob> {
            ParallelCallWithJobScreen()
        }

        composable<NavDestinationItem.ParallelCallWithAsync> {
            val args = it.toRoute<NavDestinationItem.ParallelCallWithAsync>()
            ParallelCallWithAsyncScreen(args.sampleType, args.sampleTitle)
        }

        composable<NavDestinationItem.WithContext> {
            WithContextScreen()
        }

        composable<NavDestinationItem.MultipleWithContext> {
            MultipleWithContextScreen()
        }

        composable<NavDestinationItem.CoroutineExceptionHandler> {
            val args = it.toRoute<NavDestinationItem.CoroutineExceptionHandler>()
            CoroutineExceptionHandlerScreen(args.sampleType, args.sampleTitle)
        }

        composable<NavDestinationItem.CoroutineCancellationSample> {
            val args = it.toRoute<NavDestinationItem.CoroutineCancellationSample>()
            CoroutineCancellationScreen(args.sampleType, args.sampleTitle)
        }


    }
}