package com.example.jetweather.weatherdata

import com.example.jetweather.constants.Placeholder

data class CurrentWeatherData(
    val temp: Float = Placeholder.FLOAT,
    val apparentTemp: Float = Placeholder.FLOAT,
    val location: String = Placeholder.STRING,
    val weatherStatus: Int? = Placeholder.INT,
    val minTemp: Float = Placeholder.FLOAT,
    val maxTemp: Float = Placeholder.FLOAT,
    val sunriseTime: String = Placeholder.STRING,
    val sunsetTime: String = Placeholder.STRING,
)
