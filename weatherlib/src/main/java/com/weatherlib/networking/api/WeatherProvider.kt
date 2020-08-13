package com.weatherlib.networking.api

import com.weatherlib.networking.model.WeatherModel
import io.reactivex.Single


interface WeatherProvider {

    fun getCurrentWeather(query: String): Single<WeatherModel>

}