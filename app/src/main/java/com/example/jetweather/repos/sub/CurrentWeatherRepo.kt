package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.TomTom
import com.example.jetweather.repos.BaseWeatherRepository
import com.example.jetweather.repos.UserPreferencesRepository
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
    userPreferencesRepository: UserPreferencesRepository,
): BaseWeatherRepository(locationProvider), CurrentWeatherRepo {

    private val temperatureUnit: Flow<String> = userPreferencesRepository.temperatureUnit

    override fun fetchTemp(): Flow<Float> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeatherStatus.temperature },
        defaultValue = 0f,
        temperatureUnit = temperatureUnit,
    )

    override fun fetchApparentTemp(): Flow<Float> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeatherStatus.apparentTemperature },
        defaultValue = 0f,
        temperatureUnit = temperatureUnit,
    )

    override fun fetchWeatherStatus(): Flow<Int?> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeatherStatus.weatherCode },
        defaultValue = 0,
        temperatureUnit = temperatureUnit,
    )

    override fun fetchMinTemp(): Flow<Float> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeather.minTemperature[0] },
        defaultValue = 0f,
        temperatureUnit = temperatureUnit,
    )

    override fun fetchMaxTemp(): Flow<Float> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeather.maxTemperature[0] },
        defaultValue = 0f,
        temperatureUnit = temperatureUnit,
    )

    override fun fetchSunsetTime(): Flow<String> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeather.sunsetTime[0] },
        defaultValue = "",
        temperatureUnit = temperatureUnit,
    )

    override fun fetchSunriseTime(): Flow<String> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeather.sunriseTime[0] },
        defaultValue = "",
        temperatureUnit = temperatureUnit,
    )

    override fun fetchCurrentCity(): Flow<String> = handleResponseNew(
        response = { lat, long, _ -> geolocationApi.getLocation(lat, long) },
        transform = { locationData -> locationData.addresses[0].address.city },
        defaultValue = "",
        temperatureUnit = temperatureUnit,
    )
}

