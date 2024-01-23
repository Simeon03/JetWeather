package com.example.jetweather.helper

import com.example.jetweather.R

fun weatherCodeIcon(weatherCode: Int): Int {
    return when (weatherCode) {
        0, 1 -> R.drawable.sunny
        2, 3, 45, 48 -> R.drawable.cloudy
        51, 53, 55, 56, 57, 61, 63, 65, 66, 67, 80, 81, 82 -> R.drawable.rainy
        73, 75, 77, 85, 86 -> R.drawable.snowy
        95, 96, 99 -> R.drawable.thunderstorm
        else -> R.drawable.cloudy
    }
}