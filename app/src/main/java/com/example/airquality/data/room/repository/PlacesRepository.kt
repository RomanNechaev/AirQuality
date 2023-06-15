package com.example.airquality.data.room.repository

import androidx.lifecycle.LiveData
import com.example.airquality.models.AirQuality

interface PlacesRepository {
    val allSavedCities: LiveData<List<AirQuality>>

    suspend fun insertCity(airQuality: AirQuality, onSuccess:()->Unit)
    suspend fun deleteCity(airQuality: AirQuality, onSuccess:()->Unit)
}