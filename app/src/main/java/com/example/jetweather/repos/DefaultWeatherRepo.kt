package com.example.jetweather.repos

import com.example.jetweather.model.LocationProvider
import com.example.jetweather.weatherdata.CurrentLocationData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class DefaultWeatherRepo @Inject constructor(
    locationProvider: LocationProvider,
    userPreferencesRepo: UserPreferencesRepo
) {

    private val locationFlow: StateFlow<CurrentLocationData> = locationProvider.locationFlow
    private val temperatureUnit: Flow<String> = userPreferencesRepo.temperatureUnit

    @OptIn(ExperimentalCoroutinesApi::class)
    fun <T, R> handleResponse(
        response: suspend (lat: Double, long: Double, unit: String) -> Response<T>,
        transform: (T) -> R,
        defaultValue: R,
    ): Flow<R> {
        return locationFlow.flatMapLatest { (lat, long) ->
            temperatureUnit.flatMapLatest { unit ->
                flow {
                    val data = response(lat, long, unit)
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
            }
        }.flowOn(Dispatchers.IO)
    }
}
