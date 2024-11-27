package com.example.jetweather.data.weather

import com.example.jetweather.constants.Data
import com.google.gson.annotations.SerializedName

data class CurrentHourWeather(
    @SerializedName(Data.LATITUDE) val latitude: Double,
    @SerializedName(Data.LONGITUDE) val longitude: Double,
    @SerializedName(Data.TIMEZONE) val timezone: String,
    @SerializedName(Data.HOURLY_UNITS) val units: CurrentHourUnits,
    @SerializedName(Data.HOURLY) val data: CurrentHourData,
)

data class CurrentHourUnits(
    @SerializedName("cloud_cover") val cloudCover: String,
    @SerializedName("visibility") val visibility: String,
    @SerializedName("uv_index") val uvIndex: String,
)

data class CurrentHourData(
    @SerializedName("time") val time: List<String>,
    @SerializedName("cloud_cover") val cloudCover: List<Int>,
    @SerializedName("visibility") val visibility: List<Int>,
    @SerializedName("uv_index") val uvIndex: List<Float>,
)