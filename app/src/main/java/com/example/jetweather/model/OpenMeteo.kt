package com.example.jetweather.model

import com.example.jetweather.data.weather.CurrentHourWeather
import com.example.jetweather.data.weather.CurrentWeather
import com.example.jetweather.data.weather.HourlyWeather
import com.example.jetweather.data.weather.WeeklyWeather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenMeteo {

    @GET(ENDPOINT)
    suspend fun getCurrentWeather(
        @Query(LATITUDE) latitude: Double,
        @Query(LONGITUDE) longitude: Double,
        @Query("temperature_unit") temperatureUnit: String,
        @Query(CURRENT) current: String = "temperature_2m,apparent_temperature,weather_code",
        @Query(DAILY) daily: String = "temperature_2m_max,temperature_2m_min,sunrise,sunset",
        @Query(TIMEZONE) timezone: String = "auto",
        @Query(FORECAST_DAYS) forecastDays: Int = 1,
    ): Response<CurrentWeather>

    @GET(ENDPOINT)
    suspend fun getWeeklyWeather(
        @Query(LATITUDE) latitude: Double,
        @Query(LONGITUDE) longitude: Double,
        @Query(TIMEZONE) timezone: String = "auto",
        @Query(DAILY) daily: String = "weather_code,temperature_2m_max,temperature_2m_min",
    ): Response<WeeklyWeather>

    @GET(ENDPOINT)
    suspend fun getHourlyData(
        @Query(LATITUDE) latitude: Double,
        @Query(LONGITUDE) longitude: Double,
        @Query(HOURLY) hourly: String = "temperature_2m,relative_humidity_2m,weather_code",
        @Query(FORECAST_DAYS) forecastDays: Int = 2,
    ): Response<HourlyWeather>

    @GET(ENDPOINT)
    suspend fun getCurrentHourData(
        @Query(LATITUDE) latitude: Double,
        @Query(LONGITUDE) longitude: Double,
        @Query(HOURLY) hourly: String = "cloud_cover,visibility,uv_index",
        @Query(FORECAST_DAYS) forecastDays: Int = 1,
        @Query(FORECAST_HOURS) forecastHours: Int = 1,
    ): Response<CurrentHourWeather>

    companion object {
        const val ENDPOINT = "/v1/forecast?"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val DAILY = "daily"
        const val CURRENT = "current"
        const val FORECAST_DAYS = "forecast_days"
        const val FORECAST_HOURS = "forecast_hours"
        const val TIMEZONE = "timezone"
        const val HOURLY = "hourly"
    }
}