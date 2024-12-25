package com.mustafacan.coroutinesamples.ui.samples.exceptionhandler

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
import com.mustafacan.coroutinesamples.ui.model.CoroutineExceptionSampleType
import kotlinx.coroutines.launch


@Composable
fun CoroutineExceptionHandlerScreen(sampleType: String, sampleTitle: String) {
    val viewModel = CoroutineExceptionHandlerViewModel()
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

        CatsContent(state = state)

        BirdsContent(state = state)

        CompletionTimeInfo(state = state)

        Spacer(modifier = Modifier.height(10.dp))
        if (sampleType.equals(CoroutineExceptionSampleType.WITH_JOB.name)) {
            Text(text = "Since an error occurred in the child jobs, other ongoing child jobs are cancelled before completion(For example: Dogs Content).")
        } else if (sampleType.equals(CoroutineExceptionSampleType.WITH_SUPERVISORJOB.name)) {
            Text(text = "Although there was an error in the child job, the other child job was not cancelled and was completed. Because SupervisorJob was used.")
        } else if (sampleType.equals(CoroutineExceptionSampleType.WITH_COROUTINE_SCOPE.name)) {
            Text(text = "Since an error occurred in the child jobs, other ongoing child jobs are cancelled before completion(For example: Dogs Content).")
        } else if (sampleType.equals(CoroutineExceptionSampleType.WITH_SUPERVISOR_SCOPE.name)) {
            Text(text = "Although there was an error in the child job, the other child job was not cancelled and was completed. Because supervisorScope was used.")
        }

        Button(modifier = Modifier
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally), onClick = {
            coroutineScope.launch {
                if (sampleType.equals(CoroutineExceptionSampleType.WITH_JOB.name))
                    viewModel.exceptionHandlerWithJob()
                else if (sampleType.equals(CoroutineExceptionSampleType.WITH_SUPERVISORJOB.name))
                    viewModel.exceptionHandlerWithSupervisorJob()
                else if (sampleType.equals(CoroutineExceptionSampleType.WITH_COROUTINE_SCOPE.name))
                    viewModel.exceptionHandlerWithCoroutineScope()
                else if (sampleType.equals(CoroutineExceptionSampleType.WITH_SUPERVISOR_SCOPE.name))
                    viewModel.exceptionHandlerWithSupervisorScope()

            }
        }) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = "Fetch Data"
            )
        }
    }
}