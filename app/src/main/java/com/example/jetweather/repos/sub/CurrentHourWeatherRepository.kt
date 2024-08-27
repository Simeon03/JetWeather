package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.DefaultWeatherRepo
import kotlinx.coroutines.flow.Flow

interface CurrentHourWeatherRepository {

    fun fetchCloudCover(): Flow<Int>

    fun fetchVisibility(): Flow<Int>

    fun fetchUvIndex(): Flow<Float>

}

class DefaultCurrentHourWeatherRepository(
    private val weatherApi: OpenMeteo,
    private val weatherRepo: DefaultWeatherRepo,
): CurrentHourWeatherRepository {

    override fun fetchCloudCover(): Flow<Int> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentHourData(lat, long, unit) },
        transform = { weatherData -> weatherData.data.cloudCover[0] },
        defaultValue = 0,
    )

    override fun fetchVisibility(): Flow<Int> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentHourData(lat, long, unit) },
        transform = { weatherData -> weatherData.data.visibility[0] },
        defaultValue = 0,
    )

    override fun fetchUvIndex(): Flow<Float> = weatherRepo.handleResponse(
        response = { lat, long, unit -> weatherApi.getCurrentHourData(lat, long, unit) },
        transform = { weatherData -> weatherData.data.uvIndex[0] },
        defaultValue = 0f,
    )

}