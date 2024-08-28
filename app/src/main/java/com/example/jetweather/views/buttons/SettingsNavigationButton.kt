package com.example.jetweather.views.buttons

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun SettingsNavigationButton(
    @DrawableRes icon: Int,
    description: String,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(painter = painterResource(id = icon), contentDescription = description, tint = MaterialTheme.colorScheme.primary)
    }
}