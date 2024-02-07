package com.example.jetweather.model.apiservice

import com.example.jetweather.data.CurrentWeather
import com.example.jetweather.data.HourlyWeather
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

    @GET(ENDPOINT)
    suspend fun getHourlyData(
        @Query(LATITUDE) latitude: Float,
        @Query(LONGITUDE) longitude: Float,
        @Query(HOURLY) hourly: String = "temperature_2m,relative_humidity_2m,weather_code",
        @Query(FORECAST_DAYS) forecastDays: Int = 2,
    ): Response<HourlyWeather>

    companion object {
        const val ENDPOINT = "/v1/forecast?"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val DAILY = "daily"
        const val CURRENT = "current"
        const val FORECAST_DAYS = "forecast_days"
        const val TIMEZONE = "timezone"
        const val HOURLY = "hourly"
    }
}