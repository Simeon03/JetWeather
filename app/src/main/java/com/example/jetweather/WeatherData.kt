package com.example.jetweather

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("generationtime_ms") val generationTimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @SerializedName("daily_units") val dailyUnits: DailyUnits,
    val daily: DailyData
)

data class DailyUnits(
    val time: String,
    @SerializedName("temperature_2m_max") val temperature2mMax: String,
    @SerializedName("temperature_2m_min") val temperature2mMin: String
)

data class DailyData(
    val time: List<String>,
    @SerializedName("temperature_2m_max") val temperature2mMax: List<Double>,
    @SerializedName("temperature_2m_min") val temperature2mMin: List<Double>
)

data class CurrentWeatherData(
    val latitude: Double,
    val longitude: Double,
    val current: CurrentWeather,
)

data class CurrentWeather(
    val time: String,
    @SerializedName("temperature_2m") val temperature2m: Float,
    @SerializedName("weather_code") val weatherCode: Int,
)
