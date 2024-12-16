package com.mustafacan.coroutinesamples.ui.model

import com.mustafacan.coroutinesamples.model.Bird
import com.mustafacan.coroutinesamples.model.Cat
import com.mustafacan.coroutinesamples.model.Dog

data class AnimalsUiModel(val dogs: List<Dog>, val cats: List<Cat>, val birds: List<Bird>)
