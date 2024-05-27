package com.example.jetweather.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.ui.theme.primaryP10

@Composable
fun SimpleWeatherCardLayout(
    cardChipText: String,
    cardChipIconId: Int,
    valueText: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        CardChip(
            text = cardChipText,
            iconId = cardChipIconId,
        )
        Text(
            text = valueText,
            style = Typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = primaryP10,
        )
    }

}