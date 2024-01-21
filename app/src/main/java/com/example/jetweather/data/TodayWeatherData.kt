package com.example.jetweather.data

import com.google.gson.annotations.SerializedName

data class TodayWeatherData(
    val latitude: Double,
    val longitude: Double,
    @SerializedName("daily_units") val todayTempUnits: TodayTempUnits,
    @SerializedName("daily") val todayTempData: TodayTempData,
)

data class TodayTempUnits(
    val time: String,
    @SerializedName("temperature_2m_max") val temperature2mMax: String,
    @SerializedName("temperature_2m_min") val temperature2mMin: String,
)

data class TodayTempData(
    val time: List<String>,
    @SerializedName("temperature_2m_max") val temperature2mMax: List<Float>,
    @SerializedName("temperature_2m_min") val temperature2mMin: List<Float>,
)