package com.example.jetweather.model

import com.example.jetweather.BuildConfig
import com.example.jetweather.constants.Api.REVERSE_GEOCODE_QUERY
import com.example.jetweather.constants.Api.TOM_TOM_BASE_URL
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TomTom {

    @GET("${TOM_TOM_BASE_URL}${REVERSE_GEOCODE_QUERY}{lat},{lon}.json?key=${BuildConfig.TOM_TOM_API_KEY}&radius=100")
    suspend fun getLocation(
        @Path("lat") lat: Double,
        @Path("lon") lon: Double,
    ): Response<CurrentLocations>
}

data class CurrentLocations(
    @SerializedName("addresses") val addresses: List<CurrentAddress>
)

data class CurrentAddress(
    @SerializedName("address") val address: Address,
)

data class Address(
    @SerializedName("municipalitySubdivision") val city: String,
)

