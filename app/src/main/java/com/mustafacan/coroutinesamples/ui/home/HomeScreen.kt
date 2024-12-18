package com.mustafacan.coroutinesamples.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mustafacan.coroutinesamples.ui.navigation.NavDestinationItem

@Composable
fun HomeScreen(navController: NavController) {
    val sampleList = listOf(NavDestinationItem.DispatcherSample, NavDestinationItem.ParallelCallWithJob,
        NavDestinationItem.ParallelCallWithAsync, NavDestinationItem.WithContext, NavDestinationItem.MultipleWithContext)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Coroutine Samples", modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sampleList) {
                Button(modifier = Modifier.fillMaxWidth(),
                    onClick = { navController.navigate(it) }) {
                    Text(text = it.title!!)
                }
            }
        }
    }


}