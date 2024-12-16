package com.mustafacan.coroutinesamples.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
@Composable
fun CircleImage(url: String, modifier: Modifier) {

    AsyncImage(modifier = modifier,
        model = url,
        placeholder = ColorPainter(Color.LightGray),
        error = ColorPainter(Color.LightGray),
        contentDescription = "",
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop
    )

}