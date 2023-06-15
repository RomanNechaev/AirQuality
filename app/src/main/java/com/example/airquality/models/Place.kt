package com.example.airquality.models

data class Place(
    val crowdsource: Int,
    val description: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val parent: Parent,
    val place_id: String,
    val region_lat_max: Double,
    val region_lat_min: Double,
    val region_lon_max: Double,
    val region_lon_min: Double,
    val type: String
)