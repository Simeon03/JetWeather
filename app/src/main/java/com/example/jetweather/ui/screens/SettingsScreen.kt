package com.example.jetweather.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.ui.components.selections.TemperatureSelection
import com.example.jetweather.ui.components.selections.ThemeSelection
import com.example.jetweather.ui.components.text.CurrentLocation

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
