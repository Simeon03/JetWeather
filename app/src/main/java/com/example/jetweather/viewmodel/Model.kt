package com.example.jetweather.viewmodel

import com.example.jetweather.helpers.DataFormatter.formatDay
import com.example.jetweather.helpers.DataFormatter.formatRelativeHumidityText
import com.example.jetweather.helpers.DataFormatter.formatTemperatureText
import com.example.jetweather.helpers.DataFormatter.formatTime
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToIcon
import com.example.jetweather.helpers.DataFormatter.formatWeatherCodeToText
import com.example.jetweather.helpers.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helpers.DataFormatter.getPercentageOfDay

class Model(private val viewModel: MainViewModel) {

    private val weatherData by lazy {
        viewModel.weatherData.value
    }

    val weatherDataLoading by lazy {
        viewModel.isLoading
    }

    val currentWeatherStatus by lazy {
        formatWeatherCodeToText(weatherData.currentWeatherStatus ?: 0)
    }

    val currentTemp by lazy {
        formatTemperatureText(weatherData.currentTemp)
    }

    val currentApparentTemp by lazy {
        formatTemperatureText(weatherData.currentApparentTemp)
    }

    val currentMinTemp by lazy {
        formatTemperatureText(weatherData.currentMinTemp)
    }

    val currentMaxTemp by lazy {
        formatTemperatureText(weatherData.currentMaxTemp)
    }

    val currentLocation by lazy {
        weatherData.currentLocation
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

    val weeklyMinTemp by lazy {
        weatherData.weeklyMinTemp.map { formatTemperatureText(it) }
    }

    val weeklyMaxTemp by lazy {
        weatherData.weeklyMaxTemp.map { formatTemperatureText(it) }
    }

    val weeklyDays by lazy {
        weatherData.weeklyDay.map { formatDay(it) }
    }

    val weeklyWeatherStatus by lazy {
        weatherData.weeklyWeatherStatus.map { formatWeatherCodeToIcon(it) }
    }

}