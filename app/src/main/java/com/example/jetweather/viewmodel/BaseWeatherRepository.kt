package com.example.jetweather.viewmodel

import com.example.jetweather.model.apiservice.LocationApiService
import com.example.jetweather.model.apiservice.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BaseWeatherRepository(
    private val weatherApi: WeatherApiService,
    private val googleMapsApi: LocationApiService
): WeatherRepository {
    override fun fetchLocationText(): Flow<String> = flow {
        val response = googleMapsApi.getLocationData("52.52,13.41")
        val location = response.body()?.results?.get(0)?.addressComponents?.get(0)?.shortName
        if (response.isSuccessful) {
            location?.let { emit(it) }
        } else {
            // Handle error appropriately
            emit("Location not available")
        }
    }.flowOn(Dispatchers.IO)
}