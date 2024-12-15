package com.mustafacan.coroutinesamples.ui.data

import com.mustafacan.coroutinesamples.model.Bird
import com.mustafacan.coroutinesamples.model.Cat
import com.mustafacan.coroutinesamples.model.Dog
import kotlinx.coroutines.delay

object MockRepository {

    suspend fun getCats() : List<Cat> {
        delay(2000)
        return listOf(Cat(name = "Persian", temperament = "Sweet, Gentle, Quiet"))
    }

    suspend fun getBirds() : List<Bird> {
        delay(2500)
        return listOf(Bird(name = "Eagle", habitat = "Tropical Rainforests"))
    }

    suspend fun getDogs() : List<Dog> {
        delay(5000)
        return listOf(Dog(name = "Golden Retriever", origin = "Scotland"))
    }

    suspend fun getCatsWithError() : List<Cat> {
        delay(2000)
        throw RuntimeException("An error occurred while loading cats")
    }

    suspend fun getBirdsWithError() : List<Bird> {
        delay(2500)
        throw RuntimeException("An error occurred while loading birds")
    }

    suspend fun getDogsWithError() : List<Dog> {
        delay(5000)
        throw RuntimeException("An error occurred while loading dogs")
    }

}