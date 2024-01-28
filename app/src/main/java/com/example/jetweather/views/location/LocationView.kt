package com.example.jetweather.views.location

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.jetweather.data.Geolocation
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.viewmodel.WeatherViewModel

@Composable
fun LocationView(viewModel: WeatherViewModel) {
    var location by remember { mutableStateOf<Geolocation?>(null) }

    LaunchedEffect(Unit) {
        viewModel.fetchLocationData().collect { data ->
            location = data
        }
    }

    Column {
        Text(
            text = viewModel.fetchLocationName(location),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.bodyLarge,
            color = Color(255, 255, 255),
        )
    }

}