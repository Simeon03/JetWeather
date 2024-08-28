package com.example.jetweather.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.viewmodel.CurrentWeatherViewModel
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.viewmodel.WeeklyWeatherViewModel

@Composable
fun HomeScreen(
    current: CurrentWeatherViewModel,
    weekly: WeeklyWeatherViewModel,
    hourly: HourlyWeatherViewModel,
    currentHour: CurrentHourWeatherViewModel,
) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "weather") {
        composable("weather") {
            WeatherRefreshScreen(
                navController = navController,
                current = current,
                weekly = weekly,
                hourly = hourly,
                currentHour = currentHour,
            )
        }
        composable("settings") {
            SettingsScreen(
                navController = navController,
                current = current,
                hourly = hourly,
                weekly = weekly,
            )
        }
    }
}