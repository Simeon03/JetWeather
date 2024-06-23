package com.example.jetweather.weatherdata

import com.example.jetweather.constants.Main

data class CurrentLocationData(
    val latitude: Double = Main.LATITUDE,
    val longitude: Double = Main.LONGITUDE,
)
