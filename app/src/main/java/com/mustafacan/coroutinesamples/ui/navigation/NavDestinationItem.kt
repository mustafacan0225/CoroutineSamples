package com.mustafacan.coroutinesamples.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestinationItem(val title: String? = null) {
    @Serializable
    object Home : NavDestinationItem()

    @Serializable
    object DispatcherSample : NavDestinationItem("Dispatchers Sample")

    @Serializable
    object ParallelCallWithJob : NavDestinationItem("Parallel Call With Job")

    @Serializable
    object ParallelCallWithAsync : NavDestinationItem("Parallel Call With Async")

    @Serializable
    object WithContext : NavDestinationItem("WithContext Sample")

    @Serializable
    object MultipleWithContext : NavDestinationItem("Multiple WithContext Sample")
}