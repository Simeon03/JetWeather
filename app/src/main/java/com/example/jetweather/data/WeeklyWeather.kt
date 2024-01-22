package com.example.jetweather.data

import com.google.gson.annotations.SerializedName

data class WeeklyWeather(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("daily_units") val maxMinTemperatureUnit: MaxMinTemperatureUnit,
    @SerializedName("daily") val dailyTemperature: DailyTemperature,
)

data class DailyTemperature(
    @SerializedName("time") val time: List<String>,
    @SerializedName("temperature_2m_max") val maxTemperature: List<Float>,
    @SerializedName("temperature_2m_min") val minTemperature: List<Float>,
)

