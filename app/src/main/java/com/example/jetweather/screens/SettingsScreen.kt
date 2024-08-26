package com.example.jetweather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.ui.theme.primaryP90
import com.example.jetweather.views.buttons.SettingsNavigationButton

@Composable
fun SettingsScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryP90)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
        ) {
            SettingsNavigationButton(
                icon = R.drawable.arrow_back,
                description = "Settings",
                onClick = { navController.popBackStack() }
            )
        }
    }
}
