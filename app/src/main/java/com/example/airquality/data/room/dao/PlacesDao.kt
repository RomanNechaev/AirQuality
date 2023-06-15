package com.example.airquality.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.airquality.models.AirQuality
@Dao
interface PlacesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(airQuality: AirQuality)

    @Delete
    suspend fun delete(airQuality: AirQuality)

    @Query("SELECT * FROM saved_cities")
    fun getAllSavedCities():LiveData<List<AirQuality>>
}