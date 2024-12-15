package com.mustafacan.coroutinesamples.ui.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.padding(end = 5.dp),
                onClick = { printLogClicked() }) {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = "Print Logs"
                )
            }

            Button(modifier = Modifier
                .padding(end = 5.dp),
                colors = ButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray
                ),
                onClick = { clearLogClicked() }) {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    text = "Clear Logs"
                )
            }
        }

        state.value.log?.let {
            Spacer(modifier = Modifier.height(15.dp))
            Text(modifier = Modifier.fillMaxWidth(), text = it, textAlign = TextAlign.Center)
        }

    }
}