package com.example.jetweather.helper

object DataFormatter {

    fun formatTemperatureText(temp: Float): String {
        return temp.toInt().toString() + "°"
    }

    fun formatWeatherCodeText(weatherCodeNumber: Int): String {
        return weatherCode[weatherCodeNumber] ?: "Unknown"
    }

    fun formatWeatherCodeIcon(weatherCodeNumber: Int): Int {
        return weatherCodeIcon(weatherCodeNumber)
    }

}