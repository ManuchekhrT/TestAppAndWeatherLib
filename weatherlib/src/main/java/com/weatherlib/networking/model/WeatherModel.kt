package com.weatherlib.networking.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherModel(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>,
    @SerializedName("title")
    val title: String,
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("woeid")
    val woeid: Int,
    @SerializedName("latt_long")
    val lattLong: String
)

data class ConsolidatedWeather(
    @SerializedName("id")
    val id: Long,
    @SerializedName("applicable_date")
    val applicable_date: Date,
    @SerializedName("weather_state_name")
    val weatherStateName: String,
    @SerializedName("the_temp")
    val temp: Float,
    @SerializedName("wind_speed")
    val windSpeed: Float,
    @SerializedName("wind_direction")
    val windDirection: Float,
    @SerializedName("air_pressure")
    val airPressure: Float,
    @SerializedName("humidity")
    val humidity: Float,
    @SerializedName("predictability")
    val predictability: Int
)