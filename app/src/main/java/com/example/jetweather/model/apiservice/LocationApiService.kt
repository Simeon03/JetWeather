package com.example.jetweather.model.apiservice

import com.example.jetweather.BuildConfig
import com.example.jetweather.constants.ApiConstants.API_KEY
import com.example.jetweather.constants.ApiConstants.GOOGLE_MAPS_COORDS
import com.example.jetweather.constants.ApiConstants.GOOGLE_MAPS_ENDPOINT
import com.example.jetweather.data.location.CurrentLocation
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationApiService {

    @GET(GOOGLE_MAPS_ENDPOINT)
    suspend fun getLocationData(
        @Query(GOOGLE_MAPS_COORDS) coordinates: String,
        @Query(API_KEY) apiKey: String = BuildConfig.GOOGLE_MAPS_API_KEY,
    ): Response<CurrentLocation>

}