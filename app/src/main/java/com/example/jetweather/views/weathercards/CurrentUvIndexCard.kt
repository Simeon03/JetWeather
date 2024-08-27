package com.example.jetweather.views.weathercards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.jetweather.R
import com.example.jetweather.helpers.DataFormatter.getUvIndexStatus
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.ui.theme.primaryP0
import com.example.jetweather.ui.theme.primaryP10
import com.example.jetweather.ui.theme.primaryP20
import com.example.jetweather.ui.theme.primaryP30
import com.example.jetweather.ui.theme.primaryP40
import com.example.jetweather.viewmodel.CurrentHourWeatherViewModel
import com.example.jetweather.views.ClickableWeatherCard
import com.example.jetweather.views.SimpleWeatherCardLayout
import com.example.jetweather.views.layout.VerticalProgressBar

@Composable
fun CurrentUvIndexCard(viewModel: CurrentHourWeatherViewModel, modifier: Modifier = Modifier) {
    val currentHourWeather by viewModel.currentHourWeatherData.collectAsState()

    ClickableWeatherCard(
        modifier = modifier,
        dialogTitle = stringResource(id = R.string.uv_index),
        dialogContent = { UvIndexBar() }
    ) {
        SimpleWeatherCardLayout(
            cardChipText = stringResource(id = R.string.uv_index),
            cardChipIconId = R.drawable.cloud,
            valueText = stringResource(currentHourWeather.uvIndex.toInt().getUvIndexStatus()),
        )
    }
}

@Composable
fun UvIndexBar() {
    VerticalProgressBar(
        sectionColors = listOf(primaryP40, primaryP30, primaryP20, primaryP10, primaryP0),
        sectionValues = listOf("0", "3", "6", "8", "11+"),
        sectionContents = listOf(
            { UvIndexInfo(title = "Low", description = "No protection needed") },
            { UvIndexInfo(title = "Moderate", description = "Some protection is required") },
            { UvIndexInfo(title = "High", description = "Protection is essential") },
            { UvIndexInfo(title = "Very High", description = "Extra protection is needed") },
            { UvIndexInfo(title = "Extreme", description = "Stay inside") }
        ),
        barWidth = 16.dp,
        cornerRadius = 8.dp
    )
}

@Composable
fun UvIndexInfo(
    title: String,
    description: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(text = title, color = Color.White, style = Typography.labelLarge)
        Text(text = description, color = Color.Gray, style = Typography.bodyMedium)
    }
}