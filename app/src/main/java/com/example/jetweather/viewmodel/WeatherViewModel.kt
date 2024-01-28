package com.example.jetweather.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.jetweather.data.Geolocation
import com.example.jetweather.data.CurrentWeather
import com.example.jetweather.data.WeeklyWeather
import com.example.jetweather.helper.formatTemp
import com.example.jetweather.helper.getDayOfWeek
import com.example.jetweather.helper.weatherCode
import com.example.jetweather.model.apiservice.LocationApiService
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.model.apiservice.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.getInstance(OPEN_METEO_BASE_API).create(
        WeatherApiService::class.java)
    private val googleMapsApi = RetrofitInstance.getInstance(GOOGLE_MAPS_BASE_URL).create(
        LocationApiService::class.java)

    fun fetchWeatherData(): Flow<CurrentWeather> = flow {
        val response = weatherApi.getWeatherData(52.52f, 13.41f)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            // Handle error
        }
    }.flowOn(Dispatchers.IO)

    fun fetchWeeklyWeatherData(): Flow<WeeklyWeather> = flow {
        val response = weatherApi.getWeeklyWeatherData(52.52f, 13.41f)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            // Handle error
        }
    }.flowOn(Dispatchers.IO)

    fun fetchLocationData(): Flow<Geolocation> = flow {
        val response = googleMapsApi.getLocationData("52.52,13.41")
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            Log.d("Location not fetched", response.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun fetchLocationName(geolocation: Geolocation?): String {
        return geolocation?.results?.get(0)?.addressComponents?.get(0)?.shortName ?: "Location"
    }

    fun fetchCurrentTemperature(currentWeather: CurrentWeather?): String {
        val currentTemperature = currentWeather?.data?.temperature?.toInt()
        val temperatureSuffix = currentWeather?.weatherFormat?.temperatureUnit
        val formattedTemp = formatTemp(temperatureSuffix ?: "")
        return "$currentTemperature$formattedTemp"
    }

    fun fetchMinMaxTemperature(currentWeather: CurrentWeather?): String {
        val minTemp = currentWeather?.maxMinTemperature?.minTemperature?.get(0)?.toInt()
        val maxTemp = currentWeather?.maxMinTemperature?.maxTemperature?.get(0)?.toInt()
        val temperatureSuffix = currentWeather?.weatherFormat?.temperatureUnit
        val formattedTemp = formatTemp(temperatureSuffix ?: "")
        return "$minTemp$formattedTemp/$maxTemp$formattedTemp"
    }

    fun fetchTempSuffix(weeklyWeather: WeeklyWeather?): String {
        return weeklyWeather?.maxMinTemperatureUnit?.maxTemperatureUnit ?: ""
    }

    fun fetchWeatherStatus(currentWeather: CurrentWeather?): String {
        return weatherCode[currentWeather?.data?.weatherCode].toString()
    }

    fun fetchDailyMaxTemperature(weeklyWeather: WeeklyWeather?, index: Int): Int {
        return weeklyWeather?.dailyTemperature?.maxTemperature?.get(index)?.toInt() ?: 0
    }

    fun fetchDailyMinTemperature(weeklyWeather: WeeklyWeather?, index: Int): Int {
        return weeklyWeather?.dailyTemperature?.minTemperature?.get(index)?.toInt() ?: 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchDayOfWeek(weeklyWeather: WeeklyWeather?, index: Int): String {
        return getDayOfWeek(weeklyWeather?.dailyTemperature?.time?.get(index) ?: "2023-02-01")
    }

    fun fetchDailyWeatherCode(weeklyWeather: WeeklyWeather?, index: Int): Int {
        return weeklyWeather?.dailyTemperature?.weatherCode?.get(index) ?: 0
    }

    fun fetchDailyWeatherCodeDesc(weeklyWeather: WeeklyWeather?, index: Int): String {
        return weatherCode[weeklyWeather?.dailyTemperature?.weatherCode?.get(index)] ?: "0"
    }

    companion object {
        private const val OPEN_METEO_BASE_API = "https://api.open-meteo.com/"
        private const val GOOGLE_MAPS_BASE_URL = "https://maps.googleapis.com/"
    }
}