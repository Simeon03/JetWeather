package com.example.jetweather.weatherdata

import com.example.jetweather.constants.Placeholder

data class HourlyWeatherData(
    val hourlyTemperature: List<Float> = Placeholder.LIST_FLOAT,
    val hourlyTime: List<String> = Placeholder.LIST_STRING,
    val hourlyWeatherStatus: List<Int> = Placeholder.LIST_INT,
    val hourlyHumidity: List<Int> = Placeholder.LIST_INT,
)
