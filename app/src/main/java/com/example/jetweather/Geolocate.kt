package com.example.jetweather

import com.google.gson.annotations.SerializedName

data class Geolocate(
    @SerializedName("latlng") val latlng: List<Double>,
    @SerializedName("key") val apiKey: String,
)
