package com.example.jetweather

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.constants.Api.OPEN_METEO_BASE_URL
import com.example.jetweather.constants.Api.TOM_TOM_BASE_URL
import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.model.TomTom
import com.example.jetweather.repos.DefaultWeatherRepo
import com.example.jetweather.repos.UserPreferencesRepo
import com.example.jetweather.repos.sub.DefaultCurrentHourWeatherRepo
import com.example.jetweather.repos.sub.DefaultCurrentWeatherRepository
import com.example.jetweather.repos.sub.DefaultHourlyWeatherRepository
import com.example.jetweather.repos.sub.DefaultLocationRepository
import com.example.jetweather.repos.sub.DefaultWeeklyWeatherRepository
import com.example.jetweather.screens.HomeScreen
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentLocationViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherApi = RetrofitInstance.get(OPEN_METEO_BASE_URL).create(OpenMeteo::class.java)
        val locationApi = RetrofitInstance.get(TOM_TOM_BASE_URL).create(TomTom::class.java)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val locationRepo = DefaultLocationRepository(this.applicationContext, this, fusedLocationProviderClient)

        val currentLocationViewModel = CurrentLocationViewModel(locationRepo)
        val locationProvider = LocationProvider(currentLocationViewModel)

        val userPreferencesRepo = UserPreferencesRepo(this)
        val defaultWeatherRepo = DefaultWeatherRepo(locationProvider, userPreferencesRepo)

        val currentWeatherRepository = DefaultCurrentWeatherRepository(weatherApi, locationApi, defaultWeatherRepo)
        val weeklyWeatherRepository = DefaultWeeklyWeatherRepository(weatherApi, defaultWeatherRepo)
        val hourlyWeatherRepository = DefaultHourlyWeatherRepository(this.applicationContext, weatherApi, defaultWeatherRepo)
        val currentHourWeatherRepository = DefaultCurrentHourWeatherRepo(weatherApi, defaultWeatherRepo)

        val currentViewModel = CurrentWeatherViewModel(currentWeatherRepository, userPreferencesRepo)
        val weeklyWeatherViewModel = WeeklyWeatherViewModel(weeklyWeatherRepository)
        val hourlyWeatherViewModel = HourlyWeatherViewModel(hourlyWeatherRepository)
        val currentWeatherViewModel = CurrentHourWeatherViewModel(currentHourWeatherRepository)

        setContent {
            MainScreen(
                userPreferencesRepo = userPreferencesRepo,
                current = currentViewModel,
                weekly = weeklyWeatherViewModel,
                hourly = hourlyWeatherViewModel,
                currentHour = currentWeatherViewModel
            )
        }
    }
}

@Composable
fun MainScreen(
    userPreferencesRepo: UserPreferencesRepo,
    current: CurrentWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel
) {
    val themePreference by userPreferencesRepo.themePreference.collectAsState(initial = "system_default")

    JetWeatherTheme(themePreference = themePreference) {
        HomeScreen(
            current = current,
            weekly = weekly,
            hourly = hourly,
            currentHour = currentHour,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    lateinit var userPreferencesRepo: UserPreferencesRepo
    lateinit var viewModel: CurrentWeatherViewModel
    lateinit var weeklyWeatherViewModel: WeeklyWeatherViewModel
    lateinit var hourlyWeatherViewModel: HourlyWeatherViewModel
    lateinit var currentHourWeatherViewModel: CurrentHourWeatherViewModel

    MainScreen(
        userPreferencesRepo = userPreferencesRepo,
        current = viewModel,
        weekly = weeklyWeatherViewModel,
        hourly = hourlyWeatherViewModel,
        currentHour = currentHourWeatherViewModel,
    )
}