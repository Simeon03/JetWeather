package com.example.jetweather.weatherdata

import com.example.jetweather.constants.Placeholder

data class WeeklyWeatherData(
    val weeklyMinTemp: List<Float> = Placeholder.LIST_FLOAT,
    val weeklyMaxTemp: List<Float> = Placeholder.LIST_FLOAT,
    val weeklyDay: List<String> = Placeholder.LIST_STRING,
    val weeklyWeatherStatus: List<Int> = Placeholder.LIST_INT,
)
