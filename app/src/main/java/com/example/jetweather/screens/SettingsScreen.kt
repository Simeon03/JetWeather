package com.example.jetweather.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.views.selections.TemperatureSelection
import com.example.jetweather.views.selections.ThemeSelection
import com.example.jetweather.views.text.CurrentLocation

@Composable
fun SettingsScreen(navController: NavController) {
    Screen(
        containerAppBarTitle = { CurrentLocation(text = "Settings") },
        iconResId = R.drawable.arrow_back,
        iconDesc = "Back",
        onClick = { navController.popBackStack() }
    ) {
        item { TemperatureSelection() }
        item { ThemeSelection() }
    }
}
