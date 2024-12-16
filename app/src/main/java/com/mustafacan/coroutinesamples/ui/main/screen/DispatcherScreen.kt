package com.mustafacan.coroutinesamples.ui.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafacan.coroutinesamples.ui.main.DispatcherUiStateManager
import com.mustafacan.coroutinesamples.ui.main.viewmodels.DispatcherViewModel
import kotlinx.coroutines.launch

@Composable
fun DispatcherScreen() {
    val viewModel = DispatcherViewModel()
    val state = viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()
    DispatcherContent(printLogClicked = {
        coroutineScope.launch {
            viewModel.clearLog()
            viewModel.dispatcherSamples()
        }
    }, clearLogClicked = {
        viewModel.clearLog()
    }, state = state)

}

@Composable
fun DispatcherContent(
    printLogClicked: () -> Unit,
    clearLogClicked: () -> Unit,
    state: State<DispatcherUiStateManager.DispatcherScreenState>
) {

    Column(modifier = Modifier.fillMaxSize().padding(15.dp)) {

        Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = "Dispatcher Sample", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(Modifier.fillMaxWidth().padding(top = 10.dp), horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.padding(end = 5.dp),
                onClick = { printLogClicked() }) {
                Text(modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp), text = "Dispatcher Info")
            }

            Button(modifier = Modifier.padding(end = 5.dp),
                colors = ButtonColors(containerColor = Color.Red, contentColor = Color.White, disabledContainerColor = Color.Gray, disabledContentColor = Color.LightGray),
                onClick = { clearLogClicked() }) {
                Text(modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp), text = "Clear")
            }
        }

        if (state.value.dispatcherInfoList.size > 0) {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(state.value.dispatcherInfoList) {
                    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                        Text(modifier=Modifier.fillMaxWidth(), text = "${it.dispatcherName}", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
                        Text(modifier=Modifier.fillMaxWidth(), text = "Thread: ${it.threadName}", textAlign = TextAlign.Center)
                    }

                }
            }
        }
    }

}