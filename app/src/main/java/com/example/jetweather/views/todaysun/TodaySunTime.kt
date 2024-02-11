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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetweather.helper.DataFormatter
import com.example.jetweather.helper.DataFormatter.getCurrentTimePercentage
import com.example.jetweather.helper.gradientBackground
import com.example.jetweather.ui.theme.SunGradient1
import com.example.jetweather.ui.theme.SunGradient2
import com.example.jetweather.ui.theme.SunGradient3
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun TodaySunTime(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val sunriseTime = DataFormatter.getTimeOfDay(weatherData.sunriseTime[0])
    val sunsetTime = DataFormatter.getTimeOfDay(weatherData.sunsetTime[0])
    val currentTime = getCurrentTimePercentage()

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
                    currentTime,
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
            Text(text = "Sunrise", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = sunriseTime, fontSize = 28.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Sunset", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = sunsetTime, fontSize = 28.sp)
        }

    }
}

@Composable
fun CustomProgressBar(sunrise: Float, sunset: Float, currentTime: Float) {
    val barHeight = 20.dp // The height of the progress bar
    val cornerRadius = 100.dp // Circular corners

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(barHeight)
            .clip(RoundedCornerShape(cornerRadius)) // Apply corner radius
            .background(Color.DarkGray.copy(0.9f)), // Background color for the whole bar
        contentAlignment = Alignment.CenterStart
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val outerRectangleSize = size
            val innerRectangleWidth = outerRectangleSize.width * (sunset - sunrise)
            val innerRectangleOffset = outerRectangleSize.width * sunrise
            val circleCenter = outerRectangleSize.width * currentTime
            val circleRadius = barHeight.value

            // Define gradient for the outer rectangle
            val outerGradient = Brush.horizontalGradient(
                colors = listOf(Color.DarkGray.copy(0.9f), Color.DarkGray.copy(0.9f)),
                startX = 0f,
                endX = outerRectangleSize.width
            )

            // Define gradient for the inner rectangle
            val innerGradient = Brush.horizontalGradient(
                colors = listOf(SunGradient1, SunGradient2, SunGradient3),
                startX = innerRectangleOffset,
                endX = innerRectangleOffset + innerRectangleWidth
            )

            // Draw outer rectangle with gradient
            drawRoundRect(
                brush = outerGradient,
                size = outerRectangleSize,
                cornerRadius = CornerRadius(cornerRadius.toPx())
            )

            // Draw inner rectangle with gradient
            drawRoundRect(
                brush = innerGradient,
                topLeft = Offset(x = innerRectangleOffset, y = 0f),
                size = Size(width = innerRectangleWidth, height = outerRectangleSize.height),
                cornerRadius = CornerRadius(cornerRadius.toPx())
            )

            // Draw the circle for the current time
            drawCircle(
                color = Color.White,
                radius = circleRadius,
                center = Offset(x = circleCenter, y = outerRectangleSize.height / 2)
            )
        }
    }
}
