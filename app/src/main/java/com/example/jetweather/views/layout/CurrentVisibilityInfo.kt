package com.example.jetweather.views.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetweather.R
import com.example.jetweather.views.CardChip

@Composable
fun CurrentVisibilityInfo(
    visibility: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        CardChip(
            text = "Visibility",
            iconId = R.drawable.cloud,
        )
        Text(text = visibility)
    }
}