package com.example.airquality.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "saved_cities")
data class AirQuality(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val placeId:String,
    val city: String,
    val value: String,
    val level: String
):Serializable
