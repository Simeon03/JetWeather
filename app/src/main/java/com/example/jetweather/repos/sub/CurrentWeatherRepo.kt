package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.TomTom
import com.example.jetweather.repos.DefaultWeatherRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

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

class DefaultCurrentWeatherRepository @Inject constructor(
    private val weatherApi: OpenMeteo,
    private val geolocationApi: TomTom,
    private val weatherRepo: DefaultWeatherRepo
) : CurrentWeatherRepo {

    override fun fetchTemp(): Flow<Float> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeatherStatus.temperature },
        defaultValue = 0f,
    )

    override fun fetchApparentTemp(): Flow<Float> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeatherStatus.apparentTemperature },
        defaultValue = 0f,
    )

    override fun fetchWeatherStatus(): Flow<Int?> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeatherStatus.weatherCode },
        defaultValue = 0,
    )

    override fun fetchMinTemp(): Flow<Float> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeather.minTemperature[0] },
        defaultValue = 0f,
    )

    override fun fetchMaxTemp(): Flow<Float> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeather.maxTemperature[0] },
        defaultValue = 0f,
    )

    override fun fetchSunsetTime(): Flow<String> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeather.sunsetTime[0] },
        defaultValue = "",
    )

    override fun fetchSunriseTime(): Flow<String> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentWeather(lat, long, unit) },
        transform = { weatherData -> weatherData.currentWeather.sunriseTime[0] },
        defaultValue = "",
    )

    override fun fetchCurrentCity(): Flow<String> = weatherRepo.handleResponse(
        response = { lat, long, _ -> geolocationApi.getLocation(lat, long) },
        transform = { locationData -> locationData.addresses[0].address.city },
        defaultValue = "",
    )
}

