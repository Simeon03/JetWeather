package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.RepoHelpers
import com.example.jetweather.viewmodel.CurrentLocationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface CurrentHourWeatherRepository {

    fun fetchCloudCover(): Flow<Int>

    fun fetchVisibility(): Flow<Int>

    fun fetchUvIndex(): Flow<Float>

}

class DefaultCurrentHourWeatherRepository(
    private val weatherApi: OpenMeteo,
    currentLocationViewModel: CurrentLocationViewModel,
): CurrentHourWeatherRepository, RepoHelpers() {

    private val locationFlow = currentLocationViewModel.currentLocationData

    override fun fetchCloudCover(): Flow<Int> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentHourData(lat, long),
                onSuccess = { weatherData -> emit(weatherData.data.cloudCover[0]) },
                onError = { emit(0) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchVisibility(): Flow<Int> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentHourData(lat, long),
                onSuccess = { weatherData -> emit(weatherData.data.visibility[0]) },
                onError = { emit(0) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchUvIndex(): Flow<Float> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentHourData(lat, long),
                onSuccess = { weatherData -> emit(weatherData.data.uvIndex[0]) },
                onError = { emit(0f) }
            )
        }
    }.flowOn(Dispatchers.IO)

}