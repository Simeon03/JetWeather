package com.example.jetweather.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetweather.data.CurrentWeatherData
import com.example.jetweather.WeatherViewModel
import com.example.jetweather.ui.theme.Typography

@Composable
fun CurrentWeatherView(modifier: Modifier = Modifier, viewModel: WeatherViewModel) {
    // State for current weather data
    var currentWeatherData by remember { mutableStateOf<CurrentWeatherData?>(null) }

    // Fetch and observe current weather data
    LaunchedEffect(Unit) {
        viewModel.fetchCurrentWeatherData().collect { data ->
            currentWeatherData = data
        }
    }

    // UI layout
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray), // Background to visualize the column area
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        currentWeatherData?.let {
            Text(
                text = "${it.current.temperature2m}${it.currentUnits.temperature2m}",
                style = Typography.titleLarge,
                color = Color(255, 255, 255),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(20.dp)
            )
        }
    }
}
