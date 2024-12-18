package com.mustafacan.coroutinesamples.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mustafacan.coroutinesamples.ui.home.HomeScreen
import com.mustafacan.coroutinesamples.ui.samples.dispatchers.DispatcherScreen
import com.mustafacan.coroutinesamples.ui.samples.parallelcallwithasync.ParallelCallWithAsyncScreen
import com.mustafacan.coroutinesamples.ui.samples.parallelcallwithjob.ParallelCallWithJobScreen
import com.mustafacan.coroutinesamples.ui.samples.withcontext.MultipleWithContextScreen
import com.mustafacan.coroutinesamples.ui.samples.withcontext.WithContextScreen

@Composable
fun NavigationMain() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavDestinationItem.Home
    ) {
        composable<NavDestinationItem.Home> {
            HomeScreen(navController = navController)
        }

        composable<NavDestinationItem.DispatcherSample> {
            DispatcherScreen()
        }

        composable<NavDestinationItem.ParallelCallWithJob> {
            ParallelCallWithJobScreen()
        }

        composable<NavDestinationItem.ParallelCallWithAsync> {
            ParallelCallWithAsyncScreen()
        }

        composable<NavDestinationItem.WithContext> {
            WithContextScreen()
        }

        composable<NavDestinationItem.MultipleWithContext> {
            MultipleWithContextScreen()
        }


    }
}