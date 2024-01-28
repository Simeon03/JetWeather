package com.example.jetweather.views.location

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.viewmodel.WeatherViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LocationView(viewModel: WeatherViewModel) {
    val weatherData by viewModel.weatherData.collectAsState()

    val locationText = weatherData.location

    Text(
        text = locationText,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Typography.bodyLarge,
        color = Color(255, 255, 255),
    )
}