package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.TomTom
import kotlinx.coroutines.flow.Flow

interface CurrentWeatherRepo {

    fun fetchTemp(): Flow<Float>

    fun fetchApparentTemp(): Flow<Float>

    fun fetchWeatherStatus(): Flow<Int?>

    fun fetchMinTemp(): Flow<Float>

    fun fetchMaxTemp(): Flow<Float>

    fun fetchSunsetTime(): Flow<String>

    fun fetchSunriseTime(): Flow<String>

    fun fetchCurrentCity(): Flow<String>

}

class DefaultCurrentWeatherRepository(
    private val weatherApi: OpenMeteo,
    private val geolocationApi: TomTom,
    locationProvider: LocationProvider,
): CurrentWeatherRepo, BaseWeatherRepository(locationProvider) {

    override fun fetchTemp(): Flow<Float> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentWeather(lat, long) },
        transform = { weatherData -> weatherData.currentWeatherStatus.temperature },
        defaultValue = 0f,
    )

    override fun fetchApparentTemp(): Flow<Float> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentWeather(lat, long) },
        transform = { weatherData -> weatherData.currentWeatherStatus.apparentTemperature },
        defaultValue = 0f,
    )

    override fun fetchWeatherStatus(): Flow<Int?> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentWeather(lat, long) },
        transform = { weatherData -> weatherData.currentWeatherStatus.weatherCode },
        defaultValue = 0,
    )

    override fun fetchMinTemp(): Flow<Float> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentWeather(lat, long) },
        transform = { weatherData -> weatherData.currentWeather.minTemperature[0] },
        defaultValue = 0f,
    )

    override fun fetchMaxTemp(): Flow<Float> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentWeather(lat, long) },
        transform = { weatherData -> weatherData.currentWeather.maxTemperature[0] },
        defaultValue = 0f,
    )

    override fun fetchSunsetTime(): Flow<String> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentWeather(lat, long) },
        transform = { weatherData -> weatherData.currentWeather.sunsetTime[0] },
        defaultValue = "",
    )

    override fun fetchSunriseTime(): Flow<String> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentWeather(lat, long) },
        transform = { weatherData -> weatherData.currentWeather.sunriseTime[0] },
        defaultValue = "",
    )

    override fun fetchCurrentCity(): Flow<String> = handleResponseNew(
        response = { lat, long -> geolocationApi.getLocation(lat, long) },
        transform = { locationData -> locationData.addresses[0].address.city },
        defaultValue = "",
    )
}

