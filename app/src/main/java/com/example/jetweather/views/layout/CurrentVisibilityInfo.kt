package com.example.jetweather.views.layout

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
import com.example.jetweather.R
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.ui.theme.primaryP10
import com.example.jetweather.views.CardChip

@Composable
fun CurrentVisibilityInfo(
    visibility: String
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        CardChip(
            text = "Visibility",
            iconId = R.drawable.cloud,
        )
        Text(
            text = visibility,
            style = Typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = primaryP10,
        )
    }
}