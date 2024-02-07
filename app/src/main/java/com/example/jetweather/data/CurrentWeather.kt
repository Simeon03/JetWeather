package com.example.jetweather.data

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("current_units") val weatherFormat: WeatherFormat,
    @SerializedName("current") val data: Data,
    @SerializedName("daily_units") val maxMinTemperatureUnit: MaxMinTemperatureUnit,
    @SerializedName("daily") val maxMinTemperature: DailyTemperature,
)

data class WeatherFormat(
    @SerializedName("time") val timeFormat: String,
    @SerializedName("temperature_2m") val temperatureUnit: String,
    @SerializedName("apparent_temperature") val apparentTemperatureUnit: String,
    @SerializedName("weather_code") val weatherCodeFormat: String,
)

data class Data(
    @SerializedName("temperature_2m") val temperature: Float,
    @SerializedName("apparent_temperature") val apparentTemperature: Float,
    @SerializedName("weather_code") val weatherCode: Int
)

data class MaxMinTemperatureUnit(
    @SerializedName("temperature_2m_max") val maxTemperatureUnit: String,
    @SerializedName("temperature_2m_min") val minTemperatureUnit: String,
)
