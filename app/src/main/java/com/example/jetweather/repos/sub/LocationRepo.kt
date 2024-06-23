package com.example.jetweather.repos.sub

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.jetweather.constants.Main
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface LocationRepo {

    fun fetchCurrentLocation(): Flow<Pair<Double, Double>>

}

class DefaultLocationRepository(
    private val context: Context,
    private val activity: Activity,
    private val fusedLocationProviderClient: FusedLocationProviderClient
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
            requestLocationPermission()
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

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            44
        )
    }
}