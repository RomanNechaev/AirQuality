package com.example.airquality.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.airquality.data.room.dao.PlacesDao
import com.example.airquality.models.AirQuality

@Database(entities = [AirQuality::class], version = 2)
abstract class PlacesRoomDataBase:RoomDatabase() {

    abstract fun getPlaceDao():PlacesDao

    companion object{
        private var database: PlacesRoomDataBase ?= null

        fun getInstance(context: Context): PlacesRoomDataBase{
            return if (database == null){
                database = Room
                    .databaseBuilder(context, PlacesRoomDataBase::class.java, "db")
                    .build()
                database as PlacesRoomDataBase
            }else{
                database as PlacesRoomDataBase
            }
        }
    }
}