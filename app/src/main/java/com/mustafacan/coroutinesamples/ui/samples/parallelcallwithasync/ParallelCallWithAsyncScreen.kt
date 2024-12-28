package com.mustafacan.coroutinesamples.ui.samples.parallelcallwithasync

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafacan.coroutinesamples.ui.common.composable.BirdsContent
import com.mustafacan.coroutinesamples.ui.common.composable.CatsContent
import com.mustafacan.coroutinesamples.ui.common.composable.CompletionTimeInfo
import com.mustafacan.coroutinesamples.ui.common.composable.DogsContent
import com.mustafacan.coroutinesamples.ui.model.ParallelCallAsyncSampleType
import kotlinx.coroutines.launch

@Composable
fun ParallelCallWithAsyncScreen(sampleType: String, sampleTitle: String) {
    val viewModel = ParallelCallWithAsyncViewModel()
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 10.dp)) {

        Text(text = sampleTitle, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        DogsContent(state = state)

        CatsContent(state = state)

        BirdsContent(state = state)

        CompletionTimeInfo(state = state)

        Button(modifier = Modifier.padding(top = 15.dp).align(Alignment.CenterHorizontally), onClick = {
            coroutineScope.launch {
                if (sampleType.equals(ParallelCallAsyncSampleType.ASYNC_WITH_COROUTINE_SCOPE.name))
                    viewModel.getAllAnimals()
                else if (sampleType.equals(ParallelCallAsyncSampleType.ASYNC.name))
                    viewModel.parallelCallWithAsync()
            }
        }) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = "Fetch Data"
            )
        }
    }
}
