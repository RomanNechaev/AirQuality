package com.example.airquality.data.retrofit

import com.example.airquality.data.retrofit.api.RetrofitInstance
import com.example.airquality.models.Latest
import com.example.airquality.models.PlaceModel
import com.example.airquality.models.Quality
import retrofit2.Response

class RetrofitRepository {
    suspend fun getPlace(place:String): Response<PlaceModel> {
        return RetrofitInstance.api.getPlace(place)
    }

    suspend fun getAirCondition(placeId: String): Response<Quality> {
        return RetrofitInstance.api.getAirCondition(placeId)
    }
}