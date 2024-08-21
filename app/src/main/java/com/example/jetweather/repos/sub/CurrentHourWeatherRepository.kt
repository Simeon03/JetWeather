package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.BaseWeatherRepository
import kotlinx.coroutines.flow.Flow

interface CurrentHourWeatherRepository {

    fun fetchCloudCover(): Flow<Int>

    fun fetchVisibility(): Flow<Int>

    fun fetchUvIndex(): Flow<Float>

}

class DefaultCurrentHourWeatherRepository(
    private val weatherApi: OpenMeteo,
    locationProvider: LocationProvider,
): BaseWeatherRepository(locationProvider), CurrentHourWeatherRepository {

    override fun fetchCloudCover(): Flow<Int> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentHourData(lat, long) },
        transform = { weatherData -> weatherData.data.cloudCover[0] },
        defaultValue = 0,
    )

    override fun fetchVisibility(): Flow<Int> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentHourData(lat, long) },
        transform = { weatherData -> weatherData.data.visibility[0] },
        defaultValue = 0,
    )

    override fun fetchUvIndex(): Flow<Float> = handleResponseNew(
        response = { lat, long -> weatherApi.getCurrentHourData(lat, long) },
        transform = { weatherData -> weatherData.data.uvIndex[0] },
        defaultValue = 0f,
    )

}