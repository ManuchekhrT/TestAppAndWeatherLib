package com.weatherlib.interactor

import com.weatherlib.networking.api.WeatherApi
import com.weatherlib.networking.model.WeatherModel
import com.weatherlib.networking.utils.DateUtils
import io.reactivex.Single
import javax.inject.Inject

class WeatherInteractor @Inject constructor(
    private val api: WeatherApi
) {

    fun getCurrentWeather(query: String): Single<WeatherModel> {
        return api.getLocations(query)
            .flatMap { location ->
                api.getWeather(location.first().woeid)
                    .map { response ->
                        WeatherModel(
                            response.consolidatedWeather.filter { it.applicable_date == DateUtils.getCurrentDate() },
                            response.title,
                            response.locationType,
                            response.woeid,
                            response.lattLong
                        )
                    }
            }
    }

}
