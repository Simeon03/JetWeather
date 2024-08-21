package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.repos.RepoHelpers
import com.example.jetweather.viewmodel.CurrentLocationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface HourlyWeatherRepo {

    fun fetchTemp(): Flow<List<Float>>

    fun fetchTime(): Flow<List<String>>

    fun fetchWeatherStatus(): Flow<List<Int>>

    fun fetchHumidity(): Flow<List<Int>>

}

class DefaultHourlyWeatherRepository(
    private val weatherApi: OpenMeteo,
    currentLocationViewModel: CurrentLocationViewModel,
): RepoHelpers(), HourlyWeatherRepo {

    private val locationFlow = currentLocationViewModel.currentLocationData

    override fun fetchTemp(): Flow<List<Float>> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getHourlyData(lat, long),
                onSuccess = { hourlyData ->
                    val pos = getNextDayHours(hourlyData)
                    val removedBeforeTimes = hourlyData.hourly.temperature.subList(pos, pos + 24)
                    emit(removedBeforeTimes.map { it })
                },
                onError = { emit(emptyList<Float>()) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchTime(): Flow<List<String>> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getHourlyData(lat, long),
                onSuccess = { hourlyData ->
                    val pos = getNextDayHours(hourlyData)
                    val removedBeforeTimes = formattedHoursTime(hourlyData).subList(pos, pos + 24)
                    emit(removedBeforeTimes)
                },
                onError = { emit(listOf<String>()) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchWeatherStatus(): Flow<List<Int>> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getHourlyData(lat, long),
                onSuccess = { weatherData -> emit(weatherData.hourly.weatherCode) },
                onError = { emit(listOf<Int>()) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchHumidity(): Flow<List<Int>> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getHourlyData(lat, long),
                onSuccess = { weatherData -> emit(weatherData.hourly.relativeHumidity) },
                onError = { emit(listOf<Int>()) }
            )
        }
    }.flowOn(Dispatchers.IO)
}
