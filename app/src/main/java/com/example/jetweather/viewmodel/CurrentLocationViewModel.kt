package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.repos.sub.LocationRepo
import com.example.jetweather.weatherdata.CurrentLocationData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentLocationViewModel @Inject constructor(
    private val locationRepo: LocationRepo
) : ViewModel() {

    private var isLoading = MutableStateFlow(true)
    var currentLocationData = MutableStateFlow(CurrentLocationData())

    init { fetchLocationData() }

    private fun fetchLocationData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val coordinates = locationRepo.fetchCurrentLocation().first()
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
