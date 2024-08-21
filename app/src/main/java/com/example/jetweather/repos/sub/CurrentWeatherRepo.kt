package com.example.jetweather.repos.sub

import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.TomTom
import com.example.jetweather.repos.RepoHelpers
import com.example.jetweather.viewmodel.CurrentLocationViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

@OptIn(ExperimentalCoroutinesApi::class)
class DefaultCurrentWeatherRepository(
    private val weatherApi: OpenMeteo,
    private val geolocationApi: TomTom,
    currentLocationViewModel: CurrentLocationViewModel,
): CurrentWeatherRepo, RepoHelpers() {

    private val locationFlow = currentLocationViewModel.currentLocationData

    override fun fetchTemp(): Flow<Float> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentWeather(lat, long),
                onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.temperature) },
                onError = { emit(0f) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchApparentTemp(): Flow<Float> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentWeather(lat, long),
                onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.apparentTemperature) },
                onError = { emit(0f) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchWeatherStatus(): Flow<Int?> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentWeather(lat, long),
                onSuccess = { weatherData -> emit(weatherData.currentWeatherStatus.weatherCode) },
                onError = { emit(0) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchMinTemp(): Flow<Float> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentWeather(lat, long),
                onSuccess = { weatherData -> emit(weatherData.currentWeather.minTemperature[0]) },
                onError = { emit(0f) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchMaxTemp(): Flow<Float> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentWeather(lat, long),
                onSuccess = { weatherData -> emit(weatherData.currentWeather.maxTemperature[0]) },
                onError = { emit(0f) }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchSunsetTime(): Flow<String> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentWeather(lat, long),
                onSuccess = { weatherData -> emit(weatherData.currentWeather.sunsetTime[0]) },
                onError = { emit("") }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchSunriseTime(): Flow<String> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = weatherApi.getCurrentWeather(lat, long),
                onSuccess = { weatherData -> emit(weatherData.currentWeather.sunriseTime[0]) },
                onError = { emit("") }
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun fetchCurrentCity(): Flow<String> = locationFlow.flatMapLatest { (lat, long) ->
        flow {
            handleResponse(
                response = geolocationApi.getLocation(lat, long),
                onSuccess = { locationData -> emit(locationData.addresses[0].address.city) },
                onError = { emit("") }
            )
        }
    }.flowOn(Dispatchers.IO)
}

