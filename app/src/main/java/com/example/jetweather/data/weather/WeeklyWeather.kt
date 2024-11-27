package com.example.jetweather.data.weather

import com.jetweather.core.constants.Data.DAILY_UNITS
import com.jetweather.core.constants.Data.DAILY_WEATHER
import com.jetweather.core.constants.Data.LATITUDE
import com.jetweather.core.constants.Data.LONGITUDE
import com.jetweather.core.constants.Data.MAX_TEMPERATURE
import com.jetweather.core.constants.Data.MIN_TEMPERATURE
import com.jetweather.core.constants.Data.TIME
import com.jetweather.core.constants.Data.TIMEZONE
import com.jetweather.core.constants.Data.WEATHER_CODE
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
    @SerializedName("sunrise") val sunriseTime: List<String>,
    @SerializedName("sunset") val sunsetTime: List<String>,
)

