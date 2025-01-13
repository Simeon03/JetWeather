package com.example.jetweather.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
        item { SettingsHeadingText(text = "Temperature Unit") }
        item { TemperatureSelection() }
        item { SettingsHeadingText(text = "Theme") }
        item { ThemeSelection() }
    }
}

@Composable
fun SettingsHeadingText(text: String) {
    Text(text = text, fontWeight = FontWeight.Bold, fontSize = 24.sp)
}

@Preview
@PreviewLightDark
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(navController = rememberNavController())
}
