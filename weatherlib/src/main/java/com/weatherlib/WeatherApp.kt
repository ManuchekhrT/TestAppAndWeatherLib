package com.weatherlib

import com.weatherlib.di.DaggerWeatherAppComponent
import com.weatherlib.di.modules.NetworkModule
import com.weatherlib.networking.api.WeatherImpl
import com.weatherlib.networking.api.WeatherProvider
import javax.inject.Inject

class WeatherApp {

    init {
        DaggerWeatherAppComponent
            .builder()
            .networkModule(NetworkModule())
            .build()
            .apply { inject(this@WeatherApp) }
    }

    @Inject
    internal lateinit var weatherImpl: WeatherImpl

    val weatherProvider: WeatherProvider
        get() = weatherImpl
}