package com.weatherlib.networking.api

import com.weatherlib.interactor.WeatherInteractor
import com.weatherlib.networking.model.WeatherModel
import io.reactivex.Single
import javax.inject.Inject

class WeatherImpl @Inject constructor(
    private val weatherInteractor: WeatherInteractor
) : WeatherProvider {

    override fun getCurrentWeather(query: String): Single<WeatherModel> {
        return weatherInteractor.getCurrentWeather(query)
    }

}