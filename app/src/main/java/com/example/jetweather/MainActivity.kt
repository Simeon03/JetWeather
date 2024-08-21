package com.example.jetweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.constants.Api.OPEN_METEO_BASE_URL
import com.example.jetweather.constants.Api.TOM_TOM_BASE_URL
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.model.TomTom
import com.example.jetweather.repos.sub.DefaultCurrentHourWeatherRepository
import com.example.jetweather.repos.sub.DefaultCurrentWeatherRepository
import com.example.jetweather.repos.sub.DefaultHourlyWeatherRepository
import com.example.jetweather.repos.sub.DefaultLocationRepository
import com.example.jetweather.repos.sub.DefaultWeeklyWeatherRepository
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentLocationViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.FullMainView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherApi = RetrofitInstance.get(OPEN_METEO_BASE_URL).create(OpenMeteo::class.java)
        val locationApi = RetrofitInstance.get(TOM_TOM_BASE_URL).create(TomTom::class.java)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val locationRepo = DefaultLocationRepository(this.applicationContext, this, fusedLocationProviderClient)

        val currentLocationViewModel = CurrentLocationViewModel(locationRepo)

        val currentWeatherRepository = DefaultCurrentWeatherRepository(weatherApi, locationApi, currentLocationViewModel)
        val weeklyWeatherRepository = DefaultWeeklyWeatherRepository(weatherApi, currentLocationViewModel)
        val hourlyWeatherRepository = DefaultHourlyWeatherRepository(weatherApi, currentLocationViewModel)
        val currentHourWeatherRepository = DefaultCurrentHourWeatherRepository(weatherApi, currentLocationViewModel)

        val currentViewModel = CurrentWeatherViewModel(currentWeatherRepository)
        val weeklyWeatherViewModel = WeeklyWeatherViewModel(weeklyWeatherRepository)
        val hourlyWeatherViewModel = HourlyWeatherViewModel(hourlyWeatherRepository)
        val currentWeatherViewModel = CurrentHourWeatherViewModel(currentHourWeatherRepository)

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