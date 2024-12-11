package com.example.jetweather.views.weathercards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CurrentCards() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        CurrentUvIndexCard(modifier = Modifier.weight(1f).padding(end = 8.dp))
        CurrentVisibilityCard(modifier = Modifier.weight(1f).padding(start = 8.dp))
    }
}
