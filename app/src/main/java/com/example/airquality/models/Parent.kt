package com.example.airquality.models

data class Parent(
    val crowdsource: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val place_id: String,
    val type: String
)