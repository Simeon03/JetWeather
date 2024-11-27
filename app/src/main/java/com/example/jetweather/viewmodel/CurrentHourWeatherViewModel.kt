package com.example.jetweather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetweather.usecases.FetchCloudCoverUseCase
import com.example.jetweather.usecases.FetchUvIndexUseCase
import com.example.jetweather.usecases.FetchVisibilityUseCase
import com.example.jetweather.weatherdata.CurrentHourWeatherData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentHourWeatherViewModel @Inject constructor(
    private val fetchCloudCoverUseCase: FetchCloudCoverUseCase,
    private val fetchVisibilityUseCase: FetchVisibilityUseCase,
    private val fetchUvIndexUseCase: FetchUvIndexUseCase
) : ViewModel() {

    var currentHourWeatherData = MutableStateFlow(CurrentHourWeatherData())
    var isLoading = MutableStateFlow(true)

    init {
        fetchWeatherData()
    }

    fun fetchWeatherData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val cloudCover = fetchCloudCoverUseCase()
                val visibility = fetchVisibilityUseCase()
                val uvIndex = fetchUvIndexUseCase()

                currentHourWeatherData.value = CurrentHourWeatherData(
                    cloudCover = cloudCover,
                    visibility = visibility,
                    uvIndex = uvIndex
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}
