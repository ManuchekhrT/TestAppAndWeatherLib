package com.weatherlib.networking.api

import com.weatherlib.networking.model.LocationModel
import com.weatherlib.networking.model.WeatherModel
import com.weatherlib.networking.utils.ConstantUtils
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET(ConstantUtils.LOCATION_SEARCH_API)
    fun getLocations(
        @Query(ConstantUtils.QUERY) query: String
    ): Single<List<LocationModel>>


    @GET(ConstantUtils.WEATHER_API)
    fun getWeather(
        @Path(ConstantUtils.WOEID) woeid: Int
    ): Single<WeatherModel>
}
