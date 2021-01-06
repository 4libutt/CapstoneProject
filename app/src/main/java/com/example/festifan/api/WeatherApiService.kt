package com.example.festifan.api

import com.example.festifan.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {

    @GET("api/json-data-10min.php")
    suspend fun getWeather(@Query("locatie") latlong: String? = ""): WeatherResponse.Root
}