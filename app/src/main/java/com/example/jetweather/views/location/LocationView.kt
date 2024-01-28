package com.example.jetweather.views.location

import androidx.compose.foundation.layout.Column
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

@Composable
fun LocationView(viewModel: WeatherViewModel) {
    val locationText by viewModel.currentLocationText.collectAsState()

    Column {
        Text(
            text = locationText,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.bodyLarge,
            color = Color(255, 255, 255),
        )
    }

}