package com.example.jetweather.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetweather.R
import com.example.jetweather.ui.theme.Typography
import com.example.jetweather.ui.theme.primaryP10
import com.example.jetweather.ui.theme.primaryP90

@Composable
fun CardChip(
    modifier: Modifier = Modifier,
    text: String,
    iconId: Int,
) {
    Row(
        modifier = Modifier
            .background(
                color = primaryP90,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(18.dp),
            painter = painterResource(id = iconId),
            contentDescription = "",
            tint = primaryP10,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = text,
            style = Typography.bodyMedium,
            color = primaryP10,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
fun CardChipPreview() {
    CardChip(
        text = "Day forecast",
        iconId = R.drawable.cloud,
    )
}