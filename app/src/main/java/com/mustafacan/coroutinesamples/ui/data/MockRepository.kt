package com.mustafacan.coroutinesamples.ui.data

import com.mustafacan.coroutinesamples.model.Bird
import com.mustafacan.coroutinesamples.model.Cat
import com.mustafacan.coroutinesamples.model.Dog
import com.mustafacan.coroutinesamples.ui.model.CustomExceptionBirds
import com.mustafacan.coroutinesamples.ui.model.CustomExceptionCats
import com.mustafacan.coroutinesamples.ui.model.CustomExceptionDogs
import kotlinx.coroutines.delay

object MockRepository {

    suspend fun getCats() : List<Cat> {
        delay(2000)
        return listOf(Cat(name = "Maine Coon", temperament = "Frendly, Gentle, Playful", image = "https://cdn.pixabay.com/photo/2023/09/06/17/03/maine-coon-8237571_1280.jpg"))
    }

    suspend fun getBirds() : List<Bird> {
        delay(3000)
        return listOf(Bird(name = "Crowned Crane", habitat = "Wetlands and grasslands", image = "https://cdn.pixabay.com/photo/2021/10/27/10/41/grey-crowned-crane-6746726_1280.jpg"))
    }

    suspend fun getDogs() : List<Dog> {
        delay(5000)
        return listOf(Dog(name = "Golden Retriever", origin = "Scotland", image = "https://cdn.pixabay.com/photo/2019/06/22/19/01/golden-retriever-4292254_1280.jpg"))
    }

    suspend fun getCatsWithError() : List<Cat> {
        delay(2000)
        throw CustomExceptionCats("An error occurred while loading cats")
    }

    suspend fun getBirdsWithError() : List<Bird> {
        delay(3000)
        throw CustomExceptionBirds("An error occurred while loading birds")
    }

    suspend fun getDogsWithError() : List<Dog> {
        delay(5000)
        throw CustomExceptionDogs("An error occurred while loading dogs")
    }

}