package com.mustafacan.coroutinesamples.ui.samples.withcontext

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

@Composable
fun MultipleWithContextScreen() {
    val viewModel = WithContextViewModel()
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 10.dp)) {

        Text(text = "Multiple withContext Sample", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        DogsContent(state = state)

        CatsContent(state = state)

        WithContextInfo(state = state)

        CompletionTimeInfo(state = state)

        Text(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), text = "Note: multiple withContext, runs sequentially", fontWeight = FontWeight.Bold, color = Color.Blue, textAlign = TextAlign.Center)

        Button(modifier = Modifier
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally), onClick = {
            coroutineScope.launch { viewModel.multipleWithContext() }
        }) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = "Fetch Data"
            )
        }
    }

}


