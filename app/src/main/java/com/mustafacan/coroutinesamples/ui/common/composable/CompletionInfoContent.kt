package com.mustafacan.coroutinesamples.ui.common.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustafacan.coroutinesamples.ui.samples.AnimalsUiStateManager

@Composable
fun CompletionTimeInfo(state: State<AnimalsUiStateManager.AnimalsScreenState>) {
    state.value.completionInfo?.let {

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp), text = buildAnnotatedString {
            append("Completion time: ")
            withStyle(
                style = SpanStyle(
                    fontSize = 20.sp,
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(it)
            }
        }, textAlign = TextAlign.Center)
    }
}