package com.jetweather.core.weatherdata

import com.jetweather.core.constants.Placeholder

data class HourlyWeatherData(
    val temperature: List<Float> = Placeholder.LIST_FLOAT,
    val time: List<String> = Placeholder.LIST_STRING,
    val weatherStatus: List<Int> = Placeholder.LIST_INT,
    val humidity: List<Int> = Placeholder.LIST_INT,
    val hourlyPrecipitationProbability: List<Int> = Placeholder.LIST_INT,
)
