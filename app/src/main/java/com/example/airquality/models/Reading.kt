package com.example.airquality.models

data class Reading(
    val color: String,
    val kind: String,
    val level: String,
    val name: String,
    val ratio: Double,
    val type: String,
    val unit: String,
    val value: String
)