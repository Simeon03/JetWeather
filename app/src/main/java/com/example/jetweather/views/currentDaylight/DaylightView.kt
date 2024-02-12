package com.example.jetweather.views.currentDaylight

import androidx.compose.runtime.Composable
import com.example.jetweather.helpers.views.CardGradientBg
import com.example.jetweather.viewmodel.Model
import com.example.jetweather.views.currentDaylight.layouts.Daylight

@Composable
fun DaylightView(model: Model) {
    CardGradientBg {
        Daylight(
            sunriseTime = model.sunriseTime,
            sunrisePercentage = model.sunriseTimePercentage,
            sunsetTime = model.sunsetTime,
            sunsetPercentage = model.sunsetTimePercentage,
            currentTimePercentage = model.currentTimePercentage
        )
    }
}
