package com.mustafacan.coroutinesamples.ui.samples.cancellation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafacan.coroutinesamples.ui.common.composable.BirdsContent
import com.mustafacan.coroutinesamples.ui.common.composable.CatsContent
import com.mustafacan.coroutinesamples.ui.common.composable.DogsContent
import com.mustafacan.coroutinesamples.ui.model.CoroutineCancellationSampleType
import com.mustafacan.coroutinesamples.ui.samples.AnimalsUiStateManager
import kotlinx.coroutines.launch

@Composable
fun CoroutineCancellationScreen(sampleType: String, sampleTitle: String) {
    val viewModel = CoroutineCancellationViewModel()
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .verticalScroll(
                rememberScrollState()
            )) {

        Text(text = sampleTitle, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        DogsContent(state = state)

        if (!sampleType.equals(CoroutineCancellationSampleType.WITHCONTEXT_NONCANCELLABLE.name)) {
            CatsContent(state = state)
        }

        Spacer(modifier = Modifier.height(10.dp))
        if (sampleType.equals(CoroutineCancellationSampleType.JOB_CANCELLATION.name)) {
            Text(text = "In this example, the job for cats will be canceled 1.5 seconds after the button is clicked. Since the job for dogs was not canceled, that job will be completed.")
        } else if (sampleType.equals(CoroutineCancellationSampleType.PARENT_JOB_CANCELLATION.name)) {
            Text(text = "In this example, the parent job will be canceled 1.5 seconds after the button is clicked. Since the parent job is canceled, all child jobs are canceled.")
        } else if (sampleType.equals(CoroutineCancellationSampleType.WITHCONTEXT_NONCANCELLABLE.name)) {
            Text(text = "Note: We should use withContext(NonCancellable) for all suspend calls that need to be executed even when a coroutine is in a \"Cancelling\" state (i.e. even in cancellation state).")
        } else if (sampleType.equals(CoroutineCancellationSampleType.INVOKE_ON_COMPLETION.name)) {
            Text(text = "In this example, the parent job will be canceled 1.5 seconds after the button is clicked. Since the parent job is canceled, all child jobs are canceled.")
        }

        Button(modifier = Modifier
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally), onClick = {
            coroutineScope.launch {
                if (sampleType.equals(CoroutineCancellationSampleType.JOB_CANCELLATION.name)) {
                    viewModel.cancelJobSample()
                } else if (sampleType.equals(CoroutineCancellationSampleType.PARENT_JOB_CANCELLATION.name)) {
                    viewModel.cancelParentJobSample()
                } else if (sampleType.equals(CoroutineCancellationSampleType.WITHCONTEXT_NONCANCELLABLE.name)) {
                    viewModel.withContextNonCancellable()
                } else if (sampleType.equals(CoroutineCancellationSampleType.INVOKE_ON_COMPLETION.name)) {
                    viewModel.invokeOnCompletion()
                }

            }
        }) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = "Fetch Data"
            )
        }
    }
}
