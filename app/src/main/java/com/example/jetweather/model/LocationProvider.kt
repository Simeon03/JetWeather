package com.example.jetweather.model

import com.example.jetweather.viewmodel.CurrentLocationViewModel
import com.jetweather.core.weatherdata.CurrentLocationData
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LocationProvider @Inject constructor(
    currentLocationViewModel: CurrentLocationViewModel
) {
    val locationFlow: StateFlow<CurrentLocationData> = currentLocationViewModel.currentLocationData
}
