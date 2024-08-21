package com.example.jetweather.repos.sub

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.repos.RepoHelpers
import com.example.jetweather.weatherdata.CurrentLocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseWeatherRepository(locationProvider: LocationProvider): RepoHelpers() {

    private val locationFlow: StateFlow<CurrentLocationData> = locationProvider.locationFlow

    fun <T, R> handleResponseNew(
        response: suspend (lat: Double, long: Double) -> Response<T>,
        transform: (T) -> R,
        defaultValue: R
    ): Flow<R> {
        return locationFlow.flatMapLatest { (lat, long) ->
            flow {
                val data = response(lat, long)
                if (data.isSuccessful) {
                    val body = data.body()
                    if (body != null) {
                        emit(transform(body))
                    } else {
                        emit(defaultValue)
                    }
                } else {
                    emit(defaultValue)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
