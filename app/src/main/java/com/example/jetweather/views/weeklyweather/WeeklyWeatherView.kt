package com.example.jetweather.views.weeklyweather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.jetweather.data.WeeklyWeather
import com.example.jetweather.ui.theme.Gradient1
import com.example.jetweather.ui.theme.Gradient2
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeeklyWeatherView(viewModel: WeatherViewModel) {
    var weeklyWeather by remember { mutableStateOf<WeeklyWeather?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchWeeklyWeatherData().collect { data ->
            weeklyWeather = data
        }
    }

    val gradientColors = listOf(
        Gradient1.copy(alpha = 0.8f),
        Gradient2.copy(alpha = 0.6f)
    )

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth().background(Brush.verticalGradient(gradientColors))) {
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
}

