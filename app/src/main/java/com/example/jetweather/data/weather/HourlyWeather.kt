package com.example.jetweather.data.weather

import com.example.jetweather.constants.Data.HOURLY_WEATHER
import com.example.jetweather.constants.Data.HUMIDITY
import com.example.jetweather.constants.Data.LATITUDE
import com.example.jetweather.constants.Data.LONGITUDE
import com.example.jetweather.constants.Data.PRECIPITATION_PROBABILITY
import com.example.jetweather.constants.Data.TEMPERATURE
import com.example.jetweather.constants.Data.TIME
import com.example.jetweather.constants.Data.TIMEZONE
import com.example.jetweather.constants.Data.WEATHER_CODE
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
    @SerializedName(PRECIPITATION_PROBABILITY) val precipitationProbability: List<Int>,
    @SerializedName(HUMIDITY) val relativeHumidity: List<Int>,
    @SerializedName(WEATHER_CODE) val weatherCode: List<Int>,
)