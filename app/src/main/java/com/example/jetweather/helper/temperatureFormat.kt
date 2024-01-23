package com.example.jetweather.helper

fun formatTemp(tempSuffix: String): String {
    return if (tempSuffix == "°C") "°" else "°"
}