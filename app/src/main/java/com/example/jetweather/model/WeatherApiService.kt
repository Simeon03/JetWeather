package com.example.jetweather.model

import com.example.jetweather.data.CurrentWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET(ENDPOINT)
    suspend fun getWeatherData(
        @Query(LATITUDE) latitude: Float,
        @Query(LONGITUDE) longitude: Float,
        @Query(CURRENT) current: String = "temperature_2m,apparent_temperature,weather_code",
        @Query(DAILY) daily: String = "temperature_2m_max,temperature_2m_min",
        @Query(TIMEZONE) timezone: String = "auto",
        @Query(FORECAST_DAYS) forecastDays: Int = 1,
    ): Response<CurrentWeather>

    companion object {
        const val ENDPOINT = "/v1/forecast?"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val DAILY = "daily"
        const val CURRENT = "current"
        const val FORECAST_DAYS = "forecast_days"
        const val TIMEZONE = "timezone"
    }
}