package com.jetweather.core.weatherdata

data class CurrentHourWeatherData(
    val cloudCover: Int = 0,
    val visibility: Int = 0,
    val uvIndex: Float = 0f,
)
