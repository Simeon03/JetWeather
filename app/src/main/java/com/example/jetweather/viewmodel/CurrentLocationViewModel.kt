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

    private var isLoading = MutableStateFlow(true)
    var currentLocationData = MutableStateFlow(CurrentLocationData())

    init { fetchLocationData() }

    private fun fetchLocationData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val coordinates = currentLocation.fetchCurrentLocation().first()
                currentLocationData.value = CurrentLocationData(
                    latitude = coordinates.first,
                    longitude = coordinates.second,
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}
