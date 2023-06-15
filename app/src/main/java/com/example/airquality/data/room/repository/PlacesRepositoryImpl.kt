package com.example.airquality.data.room.repository

import androidx.lifecycle.LiveData
import com.example.airquality.data.room.dao.PlacesDao
import com.example.airquality.models.AirQuality

class PlacesRepositoryImpl(private val placesDao: PlacesDao):PlacesRepository {
    override val allSavedCities: LiveData<List<AirQuality>>
        get() = placesDao.getAllSavedCities()

    override suspend fun insertCity(airQuality: AirQuality, onSuccess: () -> Unit) {
        placesDao.insert(airQuality)
        onSuccess()
    }

    override suspend fun deleteCity(airQuality: AirQuality, onSuccess: () -> Unit) {
        placesDao.delete(airQuality)
        onSuccess()
    }
}