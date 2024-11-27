package com.jetweather.core.weatherdata

import com.jetweather.core.constants.Main

data class CurrentLocationData(
    val latitude: Double = Main.LATITUDE,
    val longitude: Double = Main.LONGITUDE,
)
