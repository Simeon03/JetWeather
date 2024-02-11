package com.example.jetweather.data

import com.example.jetweather.constants.DataConstants.APPARENT_TEMPERATURE
import com.example.jetweather.constants.DataConstants.CURRENT_UNITS
import com.example.jetweather.constants.DataConstants.CURRENT_WEATHER
import com.example.jetweather.constants.DataConstants.DAILY_UNITS
import com.example.jetweather.constants.DataConstants.DAILY_WEATHER
import com.example.jetweather.constants.DataConstants.LATITUDE
import com.example.jetweather.constants.DataConstants.LONGITUDE
import com.example.jetweather.constants.DataConstants.MAX_TEMP_UNIT
import com.example.jetweather.constants.DataConstants.MIN_TEMP_UNIT
import com.example.jetweather.constants.DataConstants.TEMPERATURE
import com.example.jetweather.constants.DataConstants.TIME
import com.example.jetweather.constants.DataConstants.TIMEZONE
import com.example.jetweather.constants.DataConstants.WEATHER_CODE
import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName(LATITUDE) val latitude: Double,
    @SerializedName(LONGITUDE) val longitude: Double,
    @SerializedName(TIMEZONE) val timezone: String,
    @SerializedName(CURRENT_UNITS) val weatherFormat: WeatherFormat,
    @SerializedName(CURRENT_WEATHER) val data: Data,
    @SerializedName(DAILY_UNITS) val maxMinTemperatureUnit: MaxMinTemperatureUnit,
    @SerializedName(DAILY_WEATHER) val maxMinTemperature: DailyTemperature,
)

data class WeatherFormat(
    @SerializedName(TIME) val timeFormat: String,
    @SerializedName(TEMPERATURE) val temperatureUnit: String,
    @SerializedName(APPARENT_TEMPERATURE) val apparentTemperatureUnit: String,
    @SerializedName(WEATHER_CODE) val weatherCodeFormat: String,
)

data class Data(
    @SerializedName(TEMPERATURE) val temperature: Float,
    @SerializedName(APPARENT_TEMPERATURE) val apparentTemperature: Float,
    @SerializedName(WEATHER_CODE) val weatherCode: Int
)

data class MaxMinTemperatureUnit(
    @SerializedName(MAX_TEMP_UNIT) val maxTemperatureUnit: String,
    @SerializedName(MIN_TEMP_UNIT) val minTemperatureUnit: String,
)
