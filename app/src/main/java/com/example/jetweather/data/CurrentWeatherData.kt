package com.example.jetweather.data

import com.google.gson.annotations.SerializedName

data class CurrentWeatherData(
    val latitude: Double,
    val longitude: Double,
    val current: CurrentWeather,
    @SerializedName("current_units") val currentUnits: CurrentUnits,
)

data class CurrentWeather(
    val time: String,
    @SerializedName("temperature_2m") val temperature2m: Float,
    @SerializedName("weather_code") val weatherCode: Int,
)

data class CurrentUnits(
    val time: String,
    val interval: String,
    @SerializedName("temperature_2m") val temperature2m: String,
    @SerializedName("weather_code") val weatherCode: String
)