package com.example.jetweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.constants.Api.OPEN_METEO_BASE_URL
import com.example.jetweather.model.LocationProvider
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.repos.DefaultWeatherRepo
import com.example.jetweather.repos.UserPreferencesRepo
import com.example.jetweather.repos.sub.DefaultHourlyWeatherRepository
import com.example.jetweather.repos.sub.DefaultWeeklyWeatherRepository
import com.example.jetweather.screens.HomeScreen
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentLocationViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val currentLocationViewModel: CurrentLocationViewModel by viewModels()

    private val currentWeatherViewModel: CurrentHourWeatherViewModel by viewModels()

    private val currentViewModel: CurrentWeatherViewModel by viewModels()

    private val hourlyWeatherViewModel: HourlyWeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val weatherApi = RetrofitInstance.get(OPEN_METEO_BASE_URL).create(OpenMeteo::class.java)

        val locationProvider = LocationProvider(currentLocationViewModel)

        val userPreferencesRepo = UserPreferencesRepo(this)
        val defaultWeatherRepo = DefaultWeatherRepo(locationProvider, userPreferencesRepo)

        val weeklyWeatherRepository = DefaultWeeklyWeatherRepository(weatherApi, defaultWeatherRepo)

        val weeklyWeatherViewModel = WeeklyWeatherViewModel(weeklyWeatherRepository)

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