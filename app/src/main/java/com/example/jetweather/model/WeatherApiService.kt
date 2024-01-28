package com.example.jetweather.model

import com.example.jetweather.Geolocate
import com.example.jetweather.data.CurrentWeather
import com.example.jetweather.data.WeeklyWeather
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

    @GET(ENDPOINT)
    suspend fun getWeeklyWeatherData(
        @Query(LATITUDE) latitude: Float,
        @Query(LONGITUDE) longitude: Float,
        @Query(TIMEZONE) timezone: String = "auto",
        @Query(DAILY) daily: String = "weather_code,temperature_2m_max,temperature_2m_min",
    ): Response<WeeklyWeather>

    @GET(GOOGLE_MAPS_ENDPOINT)
    suspend fun getLocationData(
        @Query("latlng") latlng: String,
        @Query("key") apiKey: String,
    ): Response<Geolocate>

    companion object {
        const val ENDPOINT = "/v1/forecast?"
        const val GOOGLE_MAPS_ENDPOINT = "/maps/api/geocode/json?"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val DAILY = "daily"
        const val CURRENT = "current"
        const val FORECAST_DAYS = "forecast_days"
        const val TIMEZONE = "timezone"
    }
}