package com.example.jetweather.repos.sub

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.jetweather.core.constants.Main
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface LocationRepo {

    fun fetchCurrentLocation(): Flow<Pair<Double, Double>>

}

class DefaultLocationRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) : LocationRepo {

    override fun fetchCurrentLocation(): Flow<Pair<Double, Double>> = flow {
        val location = getLocation()
        if (location != null) {
            emit(location)
        } else {
            emit(Pair(Main.LATITUDE, Main.LONGITUDE))
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun getLocation(): Pair<Double, Double>? {
        val permission = ActivityCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )

        return if (permission != PackageManager.PERMISSION_GRANTED) {
            null
        } else {
            val location = fusedLocationProviderClient.lastLocation.await()
            if (location != null) {
                Pair(location.latitude, location.longitude)
            } else {
                null
            }
        }
    }
}
