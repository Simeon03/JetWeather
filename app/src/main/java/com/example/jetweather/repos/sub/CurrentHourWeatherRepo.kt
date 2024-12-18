package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.DefaultWeatherRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CurrentHourWeatherRepo {

    fun fetchCloudCover(): Flow<Int>

    fun fetchVisibility(): Flow<Int>

    fun fetchUvIndex(): Flow<Float>

}

class DefaultCurrentHourWeatherRepo @Inject constructor(
    private val weatherApi: OpenMeteo,
    private val weatherRepo: DefaultWeatherRepo
) : CurrentHourWeatherRepo {

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