package com.example.jetweather.views.todaysun

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.jetweather.helper.DataFormatter
import com.example.jetweather.helper.gradientBackground
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun TodaySunTime(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val sunriseTime = DataFormatter.getTimeOfDay(weatherData.sunriseTime[0])
    val sunsetTime = DataFormatter.getTimeOfDay(weatherData.sunsetTime[0])

    Card(
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(gradientBackground())) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                SunTimeView(
                    sunriseTime = sunriseTime,
                    sunsetTime = sunsetTime
                )
                CustomProgressBar(
                    DataFormatter.getPercentageOfDay(weatherData.sunriseTime[0]),
                    DataFormatter.getPercentageOfDay(weatherData.sunsetTime[0]),
                )
            }
        }
    }
}

@Composable
fun SunTimeView(sunriseTime: String, sunsetTime: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Sunrise")
            Text(text = sunriseTime)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Sunset")
            Text(text = sunsetTime)
        }

    }
}

@Composable
fun CustomProgressBar(sunrise: Float, sunset: Float) {
    val backgroundColor = Color(0xFF90CAF9) // The color of the background bar
    val barHeight = 20.dp // The height of the progress bar
    val cornerRadiusPx = with(LocalDensity.current) { 100.dp.toPx() } // Convert dp to px for the corner radius

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(barHeight)
            .clip(RoundedCornerShape(100)) // Circular corners
            .background(backgroundColor),
        contentAlignment = Alignment.CenterStart
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val outerRectangleSize = size
            val innerRectangleWidth = outerRectangleSize.width * (sunset - sunrise) // Width spans from 0.3f to 0.7f (0.4f width)
            val innerRectangleOffset = outerRectangleSize.width * sunrise // Start at 30% of the outer width

            // Draw outer rectangle
            drawRoundRect(
                color = Color.Blue,
                size = outerRectangleSize,
                cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
            )

            // Draw inner rectangle
            drawRoundRect(
                color = Color.Red,
                topLeft = Offset(x = innerRectangleOffset, y = 0f), // Start drawing at 30% of the outer width
                size = Size(width = innerRectangleWidth, height = outerRectangleSize.height),
                cornerRadius = CornerRadius(cornerRadiusPx, cornerRadiusPx)
            )
        }
    }
}
