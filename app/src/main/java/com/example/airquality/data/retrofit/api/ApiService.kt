package com.example.airquality.data.retrofit.api

import com.example.airquality.models.Latest
import com.example.airquality.models.PlaceModel
import com.example.airquality.models.Quality
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("place_search?lang=ru")
    suspend fun getPlace(@Query("content") name: String?): Response<PlaceModel>

    @GET("current_air_condition?lang=ru&standard=aqi_us")
    suspend fun getAirCondition(@Query(value = "place_id") placeIdParam: String): Response<Quality>
}