package com.example.jetweather.views.weeklyweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.data.WeeklyWeather
import com.example.jetweather.ui.theme.Typography

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyWeatherView(viewModel: WeatherViewModel) {
    var weeklyWeather by remember { mutableStateOf<WeeklyWeather?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchWeeklyWeatherData().collect { data ->
            weeklyWeather = data
        }
    }

    Card {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            for (i in 0..6) {
                WeeklyWeatherStats(viewModel = viewModel, weeklyWeather = weeklyWeather, index = i)
            }
        }
    }
}

