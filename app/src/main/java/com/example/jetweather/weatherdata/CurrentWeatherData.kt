package com.example.jetweather.weatherdata

import com.example.jetweather.constants.Placeholder

data class CurrentWeatherData(
    val currentTemp: Float = Placeholder.FLOAT,
    val currentApparentTemp: Float = Placeholder.FLOAT,
    val currentLocation: String = Placeholder.STRING,
    val currentWeatherStatus: Int? = Placeholder.INT,
    val currentMinTemp: Float = Placeholder.FLOAT,
    val currentMaxTemp: Float = Placeholder.FLOAT,
    val currentSunriseTime: String = Placeholder.STRING,
    val currentSunsetTime: String = Placeholder.STRING,
)
