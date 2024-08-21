package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.RepoHelpers
import com.example.jetweather.viewmodel.CurrentLocationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface WeeklyWeatherRepo {

    fun fetchMinTemp(): Flow<List<Float>>

    fun fetchMaxTemp(): Flow<List<Float>>

    fun fetchDay(): Flow<List<String>>

    fun fetchWeatherStatus(): Flow<List<Int>>

}

class DefaultWeeklyWeatherRepository(
    private val weatherApi: OpenMeteo,
    currentLocationViewModel: CurrentLocationViewModel,
): RepoHelpers(), WeeklyWeatherRepo {

    private val locationFlow = currentLocationViewModel.currentLocationData

    override fun fetchMinTemp(): Flow<List<Float>> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getWeeklyWeather(lat, long),
                onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.minTemperature) },
                onError = { emit(emptyList<Float>()) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchMaxTemp(): Flow<List<Float>> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getWeeklyWeather(lat, long),
                onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.maxTemperature) },
                onError = { emit(emptyList<Float>()) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchDay(): Flow<List<String>> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getWeeklyWeather(lat, long),
                onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.time) },
                onError = { emit(emptyList<String>()) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchWeatherStatus(): Flow<List<Int>> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getWeeklyWeather(lat, long),
                onSuccess = { weeklyWeatherData -> emit(weeklyWeatherData.dailyTemperature.weatherCode) },
                onError = { emit(emptyList<Int>()) }
            )
        }
    }.flowOn(Dispatchers.IO)
}
