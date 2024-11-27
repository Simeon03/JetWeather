package com.example.jetweather.views.weathercards.layout

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.jetweather.R

@Composable
fun ClickableWeatherCard(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondaryContainer,
    dialogTitle: String,
    dialogContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    WeatherCard(
        modifier = modifier.clickable { showDialog = true },
        color = color,
        content = content
    )

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = dialogTitle) },
            text = { dialogContent() },
            confirmButton = { TextButton(onClick = { showDialog = false }) { Text(stringResource(R.string.ok)) } }
        )
    }
}