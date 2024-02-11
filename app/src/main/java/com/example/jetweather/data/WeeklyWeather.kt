package com.example.jetweather.data

import com.example.jetweather.constants.DataConstants.DAILY_UNITS
import com.example.jetweather.constants.DataConstants.DAILY_WEATHER
import com.example.jetweather.constants.DataConstants.LATITUDE
import com.example.jetweather.constants.DataConstants.LONGITUDE
import com.example.jetweather.constants.DataConstants.MAX_TEMPERATURE
import com.example.jetweather.constants.DataConstants.MIN_TEMPERATURE
import com.example.jetweather.constants.DataConstants.TIME
import com.example.jetweather.constants.DataConstants.TIMEZONE
import com.example.jetweather.constants.DataConstants.WEATHER_CODE
import com.google.gson.annotations.SerializedName

data class WeeklyWeather(
    @SerializedName(LATITUDE) val latitude: Double,
    @SerializedName(LONGITUDE) val longitude: Double,
    @SerializedName(TIMEZONE) val timezone: String,
    @SerializedName(DAILY_UNITS) val maxMinTemperatureUnit: MaxMinTemperatureUnit,
    @SerializedName(DAILY_WEATHER) val dailyTemperature: DailyTemperature,
)

data class DailyTemperature(
    @SerializedName(TIME) val time: List<String>,
    @SerializedName(WEATHER_CODE) val weatherCode: List<Int>,
    @SerializedName(MAX_TEMPERATURE) val maxTemperature: List<Float>,
    @SerializedName(MIN_TEMPERATURE) val minTemperature: List<Float>,
)

