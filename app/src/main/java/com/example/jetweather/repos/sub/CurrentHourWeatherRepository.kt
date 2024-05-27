package com.example.jetweather.repos.sub

import com.example.jetweather.constants.Main
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.RepoHelpers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface CurrentHourWeatherRepository {

    fun fetchCloudCover(): Flow<Int>

    fun fetchVisibility(): Flow<Int>

    fun fetchUvIndex(): Flow<Float>

}

class DefaultCurrentHourWeatherRepository(
    private val weatherApi: OpenMeteo
): CurrentHourWeatherRepository, RepoHelpers() {

    override fun fetchCloudCover(): Flow<Int> = flow {
        handleResponse(
            response = weatherApi.getCurrentHourData(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.data.cloudCover[0]) },
            onError = { emit(0) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchVisibility(): Flow<Int> = flow {
        handleResponse(
            response = weatherApi.getCurrentHourData(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.data.visibility[0]) },
            onError = { emit(0) }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchUvIndex(): Flow<Float> = flow {
        handleResponse(
            response = weatherApi.getCurrentHourData(Main.LATITUDE, Main.LONGITUDE),
            onSuccess = { weatherData -> emit(weatherData.data.uvIndex[0]) },
            onError = { emit(0f) }
        )
    }.flowOn(Dispatchers.IO)

}