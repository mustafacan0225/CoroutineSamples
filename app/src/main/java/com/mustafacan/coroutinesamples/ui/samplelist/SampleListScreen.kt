package com.mustafacan.coroutinesamples.ui.samplelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mustafacan.coroutinesamples.ui.model.CoroutineExceptionSampleType
import com.mustafacan.coroutinesamples.ui.navigation.NavDestinationItem
import com.mustafacan.coroutinesamples.R

@Composable
fun SampleListScreen(navController: NavController) {
    val sampleList = listOf(
        NavDestinationItem.DispatcherSample,
        NavDestinationItem.ParallelCallWithJob,
        NavDestinationItem.ParallelCallWithAsync,
        NavDestinationItem.WithContext,
        NavDestinationItem.MultipleWithContext,
        NavDestinationItem.CoroutineExceptionHandler(
            CoroutineExceptionSampleType.WITH_JOB.name,
            CoroutineExceptionSampleType.WITH_JOB.title
        ),
        NavDestinationItem.CoroutineExceptionHandler(
            CoroutineExceptionSampleType.WITH_SUPERVISORJOB.name,
            CoroutineExceptionSampleType.WITH_SUPERVISORJOB.title
        ),
        NavDestinationItem.CoroutineExceptionHandler(
            CoroutineExceptionSampleType.WITH_COROUTINE_SCOPE.name,
            CoroutineExceptionSampleType.WITH_COROUTINE_SCOPE.title
        ),
        NavDestinationItem.CoroutineExceptionHandler(
            CoroutineExceptionSampleType.WITH_SUPERVISOR_SCOPE.name,
            CoroutineExceptionSampleType.WITH_SUPERVISOR_SCOPE.title
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Coroutine Samples",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sampleList) {
                /*Box(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)) {
                    //Spacer(modifier = Modifier.height(10.dp))
                    Button(modifier = Modifier.fillMaxWidth(),
                        onClick = { navController.navigate(it) }) {
                        Text(text = it.title!!, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                    }
                }*/
                Column(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)) {
                    Card(modifier = Modifier
                        .fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.button_color),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(50),
                        onClick = { navController.navigate(it) }
                    ) {
                        Text(
                            text = it.title!!,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp),
                            textAlign = TextAlign.Center, color = Color.White
                        )
                    }
                }


            }
        }
    }


}