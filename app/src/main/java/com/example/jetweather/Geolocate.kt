package com.example.jetweather

import com.google.gson.annotations.SerializedName

data class Geolocate(
    @SerializedName("results") val results: List<Result>
)

data class Result(
    @SerializedName("address_components") val addressComponents: List<AddressComponent>
)

data class AddressComponent(
    @SerializedName("long_name") val longName: String,
    @SerializedName("short_name") val shortName: String,
    @SerializedName("types") val types: List<String>
)
