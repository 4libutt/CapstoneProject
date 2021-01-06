package com.example.festifan.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.festifan.model.WeatherResponse
import com.example.festifan.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val mainScope = CoroutineScope(Dispatchers.Main)
    private val weatherRepository: WeatherRepository = WeatherRepository()

    private val _errorText: MutableLiveData<String> = MutableLiveData()

    val weather: LiveData<List<WeatherResponse.Weather>> = weatherRepository.weather
    val errorText: LiveData<String> get() = _errorText

    fun getWeather(latlong: String?) {
        mainScope.launch {
            try {
                weatherRepository.getWeather(latlong)
            } catch (error: Throwable) {
                _errorText.value = error.message
                Log.e("Weather error", error.cause.toString())
            }
        }
    }
}