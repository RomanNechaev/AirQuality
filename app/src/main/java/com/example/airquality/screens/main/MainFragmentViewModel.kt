package com.example.airquality.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airquality.REALIZATION
import com.example.airquality.data.retrofit.RetrofitRepository
import com.example.airquality.data.room.PlacesRoomDataBase
import com.example.airquality.data.room.repository.PlacesRepositoryImpl
import com.example.airquality.models.AirQuality
import com.example.airquality.models.Place
import com.example.airquality.models.Reading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RetrofitRepository()
    val context = application
     suspend fun getPlaceRetrofit(name: String): Place? {
        return repository.getPlace(name).body()?.places?.get(0)
    }

    suspend fun getAirConditionRetrofit(name: String): Reading? {
        var placeId = getPlaceRetrofit(name)?.place_id
        var air = placeId?.let { repository.getAirCondition(it).body()?.latest?.readings?.get(0)}
        return air
    }

    fun initDataBase() {
        val placeDao = PlacesRoomDataBase.getInstance(context).getPlaceDao()
        REALIZATION = PlacesRepositoryImpl(placeDao)
    }

    fun insert(airQuality: AirQuality, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.insertCity(airQuality){
                onSuccess()
            }
        }
    }

    fun delete(airQuality: AirQuality, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REALIZATION.deleteCity(airQuality){
                onSuccess()
            }
        }
    }
}