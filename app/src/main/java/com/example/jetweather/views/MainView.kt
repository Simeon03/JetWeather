package com.example.jetweather.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2
import com.example.jetweather.ui.theme.Gradient3
import com.example.jetweather.ui.theme.Gradient4
import com.example.jetweather.ui.theme.Gradient5
import com.example.jetweather.viewmodel.Model
import com.example.jetweather.views.currentDaylight.DaylightView
import com.example.jetweather.views.currentweather.CurrentWeatherView
import com.example.jetweather.views.dailyweather.DailyWeatherView
import com.example.jetweather.views.hourlyweather.HourlyWeatherView

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FullMainView(model: Model) {
    val isLoading by model.weatherDataLoading.collectAsState()

    val gradient = Brush.verticalGradient(
        colors = listOf(Gradient1, Gradient2, Gradient3, Gradient4, Gradient5),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        if (!isLoading) {
            LazyColumn(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                item { CurrentWeatherView(model = model) }
                item { DailyWeatherView(model = model) }
                item { HourlyWeatherView(model = model) }
                item { DaylightView(model = model) }
            }
        }
    }
}