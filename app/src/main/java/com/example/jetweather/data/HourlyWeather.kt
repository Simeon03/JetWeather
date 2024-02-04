package com.example.jetweather.data

import com.google.gson.annotations.SerializedName

data class HourlyWeather(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("hourly") val hourly: Hourly,
)

data class Hourly(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m") val temperature: List<Float>,
    @SerializedName("relative_humidity_2m") val relativeHumidity: List<Int>,
    @SerializedName("weather_code") val weatherCode: List<Int>,
)