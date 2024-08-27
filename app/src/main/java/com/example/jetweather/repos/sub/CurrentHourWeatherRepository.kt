package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.BaseWeatherRepository
import com.example.jetweather.repos.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow

interface CurrentHourWeatherRepository {

    fun fetchCloudCover(): Flow<Int>

    fun fetchVisibility(): Flow<Int>

    fun fetchUvIndex(): Flow<Float>

}

class DefaultCurrentHourWeatherRepository(
    private val weatherApi: OpenMeteo,
    locationProvider: LocationProvider,
    userPreferencesRepository: UserPreferencesRepository,
): BaseWeatherRepository(locationProvider), CurrentHourWeatherRepository {

    private val temperatureUnit: Flow<String> = userPreferencesRepository.temperatureUnit

    override fun fetchCloudCover(): Flow<Int> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentHourData(lat, long, unit) },
        transform = { weatherData -> weatherData.data.cloudCover[0] },
        defaultValue = 0,
        temperatureUnit = temperatureUnit,
    )

    override fun fetchVisibility(): Flow<Int> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentHourData(lat, long, unit) },
        transform = { weatherData -> weatherData.data.visibility[0] },
        defaultValue = 0,
        temperatureUnit = temperatureUnit,
    )

    override fun fetchUvIndex(): Flow<Float> = handleResponseNew(
        response = { lat, long, unit -> weatherApi.getCurrentHourData(lat, long, unit) },
        transform = { weatherData -> weatherData.data.uvIndex[0] },
        defaultValue = 0f,
        temperatureUnit = temperatureUnit,
    )

}