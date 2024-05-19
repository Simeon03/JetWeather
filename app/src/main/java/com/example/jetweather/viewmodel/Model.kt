package com.example.jetweather.viewmodel

import com.example.jetweather.helpers.DataFormatter.formatRelativeHumidityText
import com.example.jetweather.helpers.DataFormatter.formatTemperatureText
import com.example.jetweather.helpers.DataFormatter.formatTime
import com.example.jetweather.helpers.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helpers.DataFormatter.getPercentageOfDay

class Model(private val viewModel: MainViewModel) {

    private val weatherData = viewModel.weatherData.value

    val weatherDataLoading by lazy {
        viewModel.isLoading
    }

    val hours by lazy {
        weatherData.hourlyTime
    }

    val hourlyTemps by lazy {
        weatherData.hourlyTemperature.map { formatTemperatureText(it) }
    }

    val hourlyWeatherStatus by lazy {
        weatherData.hourlyWeatherStatus
    }

    val hourlyRelativeHumidity by lazy {
        weatherData.hourlyHumidity.map { formatRelativeHumidityText(it) }
    }

    val sunriseTime by lazy {
        formatTime(weatherData.currentSunriseTime)
    }

    val sunriseTimePercentage by lazy {
        getPercentageOfDay(weatherData.currentSunriseTime)
    }

    val sunsetTime by lazy {
        formatTime(weatherData.currentSunsetTime)
    }

    val sunsetTimePercentage by lazy {
        getPercentageOfDay(weatherData.currentSunsetTime)
    }

    val currentTimePercentage by lazy {
        getCurrentTimePercentage()
    }

}