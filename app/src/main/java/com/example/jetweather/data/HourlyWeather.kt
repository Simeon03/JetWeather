package com.example.jetweather.data

import com.example.jetweather.constants.DataConstants.HOURLY_WEATHER
import com.example.jetweather.constants.DataConstants.HUMIDITY
import com.example.jetweather.constants.DataConstants.LATITUDE
import com.example.jetweather.constants.DataConstants.LONGITUDE
import com.example.jetweather.constants.DataConstants.TEMPERATURE
import com.example.jetweather.constants.DataConstants.TIME
import com.example.jetweather.constants.DataConstants.TIMEZONE
import com.example.jetweather.constants.DataConstants.WEATHER_CODE
import com.google.gson.annotations.SerializedName

data class HourlyWeather(
    @SerializedName(LATITUDE) val latitude: Double,
    @SerializedName(LONGITUDE) val longitude: Double,
    @SerializedName(TIMEZONE) val timezone: String,
    @SerializedName(HOURLY_WEATHER) val hourly: Hourly,
)

data class Hourly(
    @SerializedName(TIME) val time: List<String>,
    @SerializedName(TEMPERATURE) val temperature: List<Float>,
    @SerializedName(HUMIDITY) val relativeHumidity: List<Int>,
    @SerializedName(WEATHER_CODE) val weatherCode: List<Int>,
)