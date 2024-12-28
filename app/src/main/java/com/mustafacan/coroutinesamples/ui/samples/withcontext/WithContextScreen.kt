package com.mustafacan.coroutinesamples.ui.samples.withcontext

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mustafacan.coroutinesamples.ui.common.composable.CircleImage
import com.mustafacan.coroutinesamples.ui.common.composable.ErrorText
import kotlinx.coroutines.launch

@Composable
fun WithContextScreen() {
    val viewModel = WithContextViewModel()
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsStateWithLifecycle()
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 10.dp)) {

        Text(text = "withContext Sample", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        DogsContent(state = state)

        CatsContent(state = state)

        WithContextInfo(state = state)

        CompletionTimeInfo(state = state)

        Button(modifier = Modifier
            .padding(top = 15.dp)
            .align(Alignment.CenterHorizontally), onClick = {
            coroutineScope.launch { viewModel.singleWithContext() }
        }) {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                text = "Fetch Data"
            )
        }
    }

}

@Composable
fun WithContextInfo(state: State<WithContextUiStateManager.WithContextScreenState>) {
    state.value.beforeWithContext?.let {
        Text(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) { appendLine("Before withContext") }
            append("$it")
        }, textAlign = TextAlign.Center)
    }

    state.value.afterWithContext?.let {
        Text(modifier = Modifier.fillMaxWidth().padding(top = 15.dp), text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                )
            ) { appendLine("After withContext") }
            append("$it")
        }, textAlign = TextAlign.Center)
    }

}

@Composable
fun CompletionTimeInfo(state: State<WithContextUiStateManager.WithContextScreenState>) {
    state.value.completionInfo?.let {

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp), text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                appendLine("Completion time")
            }
            withStyle(style = SpanStyle(fontSize = 20.sp, color = Color.Red, fontWeight = FontWeight.Bold)) {
                append(it)
            }
        }, fontSize = 14.sp, textAlign = TextAlign.Center)
    }
}

@Composable
fun CatsContent(state: State<WithContextUiStateManager.WithContextScreenState>) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp)
    ) {
        Text(text = "Cats Content")
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 25.dp),
                verticalArrangement = Arrangement.Center
            ) {
                state.value.loadingCats?.let {
                    if (it) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CircularProgressIndicator(modifier = Modifier.padding(10.dp))
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Loading, please wait..."
                            )
                        }
                    }
                }

                state.value.errorMessageForCats?.let { ErrorText(error = it) }

                state.value.catList?.let {
                    val animal = it.get(0)
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                        CircleImage(
                            url = animal.image,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )

                        Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Text("${animal.name}", fontWeight = FontWeight.Bold)
                            Text("${animal.temperament}")
                        }
                    }
                }

            }

        }
    }
}

@Composable
fun DogsContent(state: State<WithContextUiStateManager.WithContextScreenState>) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Text(text = "Dogs Content")
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(1.dp, Color.LightGray),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 25.dp),
                verticalArrangement = Arrangement.Center
            ) {
                state.value.loadingDogs?.let {
                    if (it) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CircularProgressIndicator(modifier = Modifier.padding(10.dp))
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = "Loading, please wait..."
                            )
                        }

                    }
                }

                state.value.errorMessageForDogs?.let { ErrorText(error = it) }

                state.value.dogList?.let {
                    val animal = it.get(0)
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                        CircleImage(
                            url = animal.image,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                        )

                        Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Text("${animal.name}", fontWeight = FontWeight.Bold)
                            Text("${animal.origin}")
                        }

                    }



                }

            }

        }
    }
}

