package com.example.jetweather.weatherdata

import com.example.jetweather.constants.Placeholder

data class WeeklyWeatherData(
    val minTemp: List<Float> = Placeholder.LIST_FLOAT,
    val maxTemp: List<Float> = Placeholder.LIST_FLOAT,
    val day: List<String> = Placeholder.LIST_STRING,
    val weatherStatus: List<Int> = Placeholder.LIST_INT,
)
