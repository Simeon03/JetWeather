package com.example.jetweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetweather.constants.Api.OPEN_METEO_BASE_URL
import com.example.jetweather.model.OpenMeteo
import com.example.jetweather.model.RetrofitInstance
import com.example.jetweather.repos.main.DefaultCurrentWeatherRepository
import com.example.jetweather.repos.main.DefaultHourlyWeatherRepository
import com.example.jetweather.repos.main.DefaultWeeklyWeatherRepository
import com.example.jetweather.ui.theme.JetWeatherTheme
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel
import com.example.jetweather.views.FullMainView

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherApi = RetrofitInstance.get(OPEN_METEO_BASE_URL).create(
            OpenMeteo::class.java
        )
        val currentWeatherRepository = DefaultCurrentWeatherRepository(weatherApi)
        val weeklyWeatherRepository = DefaultWeeklyWeatherRepository(weatherApi)
        val hourlyWeatherRepository = DefaultHourlyWeatherRepository(weatherApi)

        val currentViewModel = CurrentWeatherViewModel(currentWeatherRepository)
        val weeklyWeatherViewModel = WeeklyWeatherViewModel(weeklyWeatherRepository)
        val hourlyWeatherViewModel = HourlyWeatherViewModel(hourlyWeatherRepository)

        setContent {
            JetWeatherTheme {
                FullMainView(
                    current = currentViewModel,
                    weeklyWeatherViewModel = weeklyWeatherViewModel,
                    hourlyWeatherViewModel = hourlyWeatherViewModel
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
    JetWeatherTheme {
        FullMainView(
            current = viewModel,
            weeklyWeatherViewModel = weeklyWeatherViewModel,
            hourlyWeatherViewModel = hourlyWeatherViewModel
        )
    }
}