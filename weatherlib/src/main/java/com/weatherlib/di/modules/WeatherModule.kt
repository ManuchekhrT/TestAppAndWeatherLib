package com.weatherlib.di.modules

import com.weatherlib.networking.api.WeatherProvider
import com.weatherlib.networking.api.WeatherImpl
import com.weatherlib.interactor.WeatherInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherModule {

    @Provides
    @Singleton
    internal fun provideWeather(weatherInteractor: WeatherInteractor): WeatherProvider =
        WeatherImpl(weatherInteractor)

}