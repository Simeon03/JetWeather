package com.example.jetweather.model.apiservice

import com.example.jetweather.BuildConfig
import com.example.jetweather.data.Geolocate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {
    @GET(ENDPOINT)
    suspend fun getLocationData(
        @Query(COORDINATES) coordinates: String,
        @Query(API_KEY) apiKey: String = BuildConfig.GOOGLE_MAPS_API_KEY,
    ): Response<Geolocate>

    companion object {
        const val ENDPOINT = "/maps/api/geocode/json?"
        const val COORDINATES = "latlng"
        const val API_KEY = "key"
    }
}