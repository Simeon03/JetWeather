package com.example.jetweather.views.hourlyweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.primaryP80
import com.example.jetweather.viewmodel.HourlyWeatherViewModel
import com.example.jetweather.views.hourlyweather.layouts.HourlyWeatherInfo

@Composable
fun HourlyWeatherView(viewModel: HourlyWeatherViewModel) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = primaryP80,
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(24) { index ->
                HourlyWeatherInfo(viewModel = viewModel, index = index)
            }
        }
    }
}
