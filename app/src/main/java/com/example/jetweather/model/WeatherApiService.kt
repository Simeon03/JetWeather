package com.example.jetweather.model

import com.example.jetweather.data.CurrentWeatherData
import com.example.jetweather.data.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("/v1/forecast?")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
        @Query("daily") daily: String,
    ): Response<WeatherData>

    @GET("/v1/forecast?")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Float,
        @Query("longitude") longitude: Float,
        @Query("current") current: String,
    ): Response<CurrentWeatherData>
}