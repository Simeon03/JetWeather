package com.example.jetweather.helper

object DataFormatter {

    fun formatTemperatureText(temp: Float): String {
        return temp.toInt().toString() + "°"
    }

}