package com.example.jetweather.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.jetweather.BuildConfig
import com.example.jetweather.Geolocate
import com.example.jetweather.data.CurrentWeather
import com.example.jetweather.data.WeeklyWeather
import com.example.jetweather.helper.formatTemp
import com.example.jetweather.helper.getDayOfWeek
import com.example.jetweather.helper.weatherCode
import com.example.jetweather.model.WeatherApiService
import com.example.jetweather.model.WeatherInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WeatherViewModel : ViewModel() {

    private val weatherApi = WeatherInstance.getInstance().create(WeatherApiService::class.java)
    private val googleMapsApi = WeatherInstance.getMapsInstance().create(WeatherApiService::class.java)

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

    fun fetchLocationData(): Flow<Geolocate> = flow {
        val response = googleMapsApi.getLocationData("52.52,13.41", BuildConfig.GOOGLE_MAPS_API_KEY)
        if (response.isSuccessful) {
            response.body()?.let { emit(it) }
        } else {
            Log.d("Location not fetched", response.toString())
        }
    }.flowOn(Dispatchers.IO)

    fun fetchLocationName(geolocate: Geolocate?): String {
        return geolocate?.results?.get(0)?.addressComponents?.get(0)?.shortName ?: "Location"
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
}