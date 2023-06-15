package com.example.airquality.models

data class Latest(
    val readings: List<Reading>,
    val update_time: String
)