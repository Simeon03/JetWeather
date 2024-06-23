package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.DefaultLocationRepository
import com.example.jetweather.weatherdata.CurrentLocationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CurrentLocationViewModel(
    private val currentLocation: DefaultLocationRepository,
) : ViewModel() {

    var currentLocationData = MutableStateFlow(CurrentLocationData())
    private var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                currentLocation.apply {
                    val location = fetchCurrentLocation().first()

                    currentLocationData.value = CurrentLocationData(
                        latitude = location.first,
                        longitude = location.second,
                    )
                }
            } finally {
                isLoading.value = false
            }
        }
    }
}