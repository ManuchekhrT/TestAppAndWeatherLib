package com.weatherlib.di

import com.weatherlib.WeatherApp
import com.weatherlib.di.modules.NetworkModule
import com.weatherlib.di.modules.WeatherModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        WeatherModule::class
    ]
)
interface WeatherAppComponent {
    fun inject(weatherApp: WeatherApp)
}