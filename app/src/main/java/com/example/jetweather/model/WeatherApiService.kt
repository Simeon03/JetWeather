package com.example.jetweather.model

import com.example.jetweather.data.CurrentWeatherData
import com.example.jetweather.data.TodayWeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET(ENDPOINT)
    suspend fun getTodayWeatherData(
        @Query(LATITUDE) latitude: Float,
        @Query(LONGITUDE) longitude: Float,
        @Query(DAILY) daily: String,
        @Query(FORECAST_DAYS) forecastDays: Int = 1,
    ): Response<TodayWeatherData>

    @GET(ENDPOINT)
    suspend fun getCurrentWeather(
        @Query(LATITUDE) latitude: Float,
        @Query(LONGITUDE) longitude: Float,
        @Query(CURRENT) current: String,
    ): Response<CurrentWeatherData>

    companion object {
        const val ENDPOINT = "/v1/forecast?"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val DAILY = "daily"
        const val CURRENT = "current"
        const val FORECAST_DAYS = "forecast_days"
    }
}