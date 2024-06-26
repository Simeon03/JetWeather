package com.example.jetweather.data.weather

import com.example.jetweather.constants.Data.APPARENT_TEMPERATURE
import com.example.jetweather.constants.Data.CURRENT_UNITS
import com.example.jetweather.constants.Data.CURRENT_WEATHER_STATUS
import com.example.jetweather.constants.Data.DAILY_UNITS
import com.example.jetweather.constants.Data.DAILY_WEATHER
import com.example.jetweather.constants.Data.LATITUDE
import com.example.jetweather.constants.Data.LONGITUDE
import com.example.jetweather.constants.Data.MAX_TEMP_UNIT
import com.example.jetweather.constants.Data.MIN_TEMP_UNIT
import com.example.jetweather.constants.Data.TEMPERATURE
import com.example.jetweather.constants.Data.TIME
import com.example.jetweather.constants.Data.TIMEZONE
import com.example.jetweather.constants.Data.WEATHER_CODE
import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName(LATITUDE) val latitude: Double,
    @SerializedName(LONGITUDE) val longitude: Double,
    @SerializedName(TIMEZONE) val timezone: String,
    @SerializedName(CURRENT_UNITS) val weatherFormat: WeatherFormat,
    @SerializedName(CURRENT_WEATHER_STATUS) val currentWeatherStatus: CurrentWeatherStatus,
    @SerializedName(DAILY_UNITS) val maxMinTemperatureUnit: MaxMinTemperatureUnit,
    @SerializedName(DAILY_WEATHER) val currentWeather: DailyTemperature,
)

data class WeatherFormat(
    @SerializedName(TIME) val timeFormat: String,
    @SerializedName(TEMPERATURE) val temperatureUnit: String,
    @SerializedName(APPARENT_TEMPERATURE) val apparentTemperatureUnit: String,
    @SerializedName(WEATHER_CODE) val weatherCodeFormat: String,
)

data class CurrentWeatherStatus(
    @SerializedName(TEMPERATURE) val temperature: Float,
    @SerializedName(APPARENT_TEMPERATURE) val apparentTemperature: Float,
    @SerializedName(WEATHER_CODE) val weatherCode: Int
)

data class MaxMinTemperatureUnit(
    @SerializedName(MAX_TEMP_UNIT) val maxTemperatureUnit: String,
    @SerializedName(MIN_TEMP_UNIT) val minTemperatureUnit: String,
)
