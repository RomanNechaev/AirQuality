package com.example.airquality.screens.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.airquality.REALIZATION
import com.example.airquality.data.room.repository.PlacesRepositoryImpl
import com.example.airquality.models.AirQuality

class SavedFragmentViewModel:ViewModel() {
    fun getAllSavedCondition(): LiveData<List<AirQuality>> {
        return REALIZATION.allSavedCities
    }
}