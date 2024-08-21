package com.example.jetweather.model

import com.example.jetweather.viewmodel.CurrentLocationViewModel
import com.example.jetweather.weatherdata.CurrentLocationData
import kotlinx.coroutines.flow.StateFlow

class LocationProvider(currentLocationViewModel: CurrentLocationViewModel) {
    val locationFlow: StateFlow<CurrentLocationData> = currentLocationViewModel.currentLocationData
}
