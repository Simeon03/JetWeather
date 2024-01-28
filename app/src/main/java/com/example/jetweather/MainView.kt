package com.example.jetweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2
import com.example.jetweather.ui.theme.Gradient3
import com.example.jetweather.ui.theme.Gradient4
import com.example.jetweather.ui.theme.Gradient5
import com.example.jetweather.viewmodel.WeatherViewModel
import com.example.jetweather.views.currentweather.CurrentWeatherView
import com.example.jetweather.views.location.LocationView
import com.example.jetweather.views.weeklyweather.WeeklyWeatherView

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainView(viewModel: WeatherViewModel) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Gradient1, Gradient2, Gradient3, Gradient4, Gradient5),
        startY = 0f,
        endY = Float.POSITIVE_INFINITY
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .systemBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier.padding(20.dp)
        ) {
            item {
                LocationView(viewModel = viewModel)
                CurrentWeatherView(viewModel = viewModel)
                Spacer(modifier = Modifier.padding(vertical = 24.dp))
                WeeklyWeatherView(viewModel = viewModel)
            }
        }
    }
}