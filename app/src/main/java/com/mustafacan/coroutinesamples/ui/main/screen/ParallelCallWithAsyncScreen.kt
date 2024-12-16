package com.mustafacan.coroutinesamples.ui.main.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafacan.coroutinesamples.ui.main.screen.birds.BirdsContent
import com.mustafacan.coroutinesamples.ui.main.screen.cats.CatsContent
import com.mustafacan.coroutinesamples.ui.main.screen.dogs.DogsContent
import com.mustafacan.coroutinesamples.ui.main.viewmodels.ParallelCallWithAsyncViewModel
import kotlinx.coroutines.launch

@Composable
fun ParallelCallWithAsyncScreen() {
    val viewModel = ParallelCallWithAsyncViewModel()
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 10.dp)) {
        Text(text = "Parallel Call With Async", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        DogsContent(state = state)
        CatsContent(state = state)
        BirdsContent(state = state)

        CompletionTimeInfo(state = state)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp), horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.padding(end = 5.dp), onClick = {
                coroutineScope.launch { viewModel.getAllData() }
            }) {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = "Fetch Data"
                )
            }
        }
    }
}
