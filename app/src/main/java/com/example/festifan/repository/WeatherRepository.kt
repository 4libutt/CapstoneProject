package com.example.festifan.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.festifan.api.WeatherApi
import com.example.festifan.api.WeatherApiService
import com.example.festifan.model.WeatherResponse
import kotlinx.coroutines.withTimeout

class WeatherRepository {

    private val weatherApiService: WeatherApiService = WeatherApi.createApi()
    private val _weather: MutableLiveData<List<WeatherResponse.Weather>> = MutableLiveData()


    val weather: LiveData<List<WeatherResponse.Weather>>
        get() = _weather

    suspend fun getWeather(latlong: String?) {
        try {
            val parsedWeatherResponse  = withTimeout(5_000) {
                weatherApiService.getWeather(latlong)
            }

            _weather.value = parsedWeatherResponse.results

        } catch (error: Throwable) {
            throw WeatherError("Unable to get weather", error)
        }
    }
}

class WeatherError(message: String, cause: Throwable) : Throwable(message, cause)