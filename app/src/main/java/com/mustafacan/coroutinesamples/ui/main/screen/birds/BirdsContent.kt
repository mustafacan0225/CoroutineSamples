package com.mustafacan.coroutinesamples.ui.main.screen.birds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mustafacan.coroutinesamples.ui.main.MainScreenUiStateManager

@Composable
fun BirdsContent(state: State<MainScreenUiStateManager.MainScreenState>) {
    Column(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp)
    ) {
        Text(text = "Birds Content", fontWeight = FontWeight.Bold)
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
                state.value.loadingBirds?.let {
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

                state.value.errorMessageForBirds?.let { Text(text = "Error Message: $it") }

                state.value.birdList?.let {
                    Text("Birds size: ${it.size}")
                    Text("Bird Name: ${it.get(0).name}")
                    Text("Bird Habitat: ${it.get(0).habitat}")
                }

            }

        }
    }
}