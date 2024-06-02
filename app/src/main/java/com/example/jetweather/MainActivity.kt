package com.example.jetweather

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import com.example.jetweather.constants.Api.OPEN_METEO_BASE_URL
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.repos.sub.DefaultCurrentHourWeatherRepository
import com.example.jetweather.repos.sub.DefaultCurrentWeatherRepository
import com.example.jetweather.repos.sub.DefaultHourlyWeatherRepository
import com.example.jetweather.repos.sub.DefaultWeeklyWeatherRepository
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.FullMainView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.properties.Delegates

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var latitude by Delegates.notNull<Double>()
    private var longitude by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherApi = RetrofitInstance.get(OPEN_METEO_BASE_URL).create(OpenMeteo::class.java)

        val currentWeatherRepository = DefaultCurrentWeatherRepository(weatherApi)
        val weeklyWeatherRepository = DefaultWeeklyWeatherRepository(weatherApi)
        val hourlyWeatherRepository = DefaultHourlyWeatherRepository(weatherApi)
        val currentHourWeatherRepository = DefaultCurrentHourWeatherRepository(weatherApi)

        val currentViewModel = CurrentWeatherViewModel(currentWeatherRepository)
        val weeklyWeatherViewModel = WeeklyWeatherViewModel(weeklyWeatherRepository)
        val hourlyWeatherViewModel = HourlyWeatherViewModel(hourlyWeatherRepository)
        val currentWeatherViewModel = CurrentHourWeatherViewModel(currentHourWeatherRepository)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        lifecycleScope.launch {
            val location = getLocation()
            if (location != null) {
                latitude = location.first
                longitude = location.second
                Log.d("MainActivity", "Location: $latitude, $longitude")
            } else {
                Log.d("MainActivity", "Failed to retrieve location")
            }

            setContent {
                JetWeatherTheme {
                    FullMainView(
                        current = currentViewModel,
                        weeklyWeatherViewModel = weeklyWeatherViewModel,
                        hourlyWeatherViewModel = hourlyWeatherViewModel,
                        currentHour = currentWeatherViewModel,
                    )
                }
            }
        }
    }

    private suspend fun getLocation(): Pair<Double, Double>? {
        return if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
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
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            44
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    lateinit var viewModel: CurrentWeatherViewModel
    lateinit var weeklyWeatherViewModel: WeeklyWeatherViewModel
    lateinit var hourlyWeatherViewModel: HourlyWeatherViewModel
    lateinit var currentHourWeatherViewModel: CurrentHourWeatherViewModel

    JetWeatherTheme {
        FullMainView(
            current = viewModel,
            weeklyWeatherViewModel = weeklyWeatherViewModel,
            hourlyWeatherViewModel = hourlyWeatherViewModel,
            currentHour = currentHourWeatherViewModel,
        )
    }
}