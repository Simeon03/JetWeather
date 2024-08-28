package com.example.jetweather.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.primaryP10
import com.example.jetweather.views.buttons.SettingsNavigationButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContainerAppBar(
    title: @Composable () -> Unit,
    @DrawableRes iconResId: Int,
    iconDesc: String,
    onClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 20.dp),
        colors = TopAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = primaryP10,
            actionIconContentColor = Color.Unspecified,
        ),
        title = title,
        navigationIcon = {
            SettingsNavigationButton(
                icon = iconResId,
                description = iconDesc,
                onClick = onClick
            )
        }
    )
}

@Composable
fun ScreenContent(content: LazyListScope.() -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) { content() }
}

@Composable
fun Screen(
    containerAppBarTitle: @Composable () -> Unit,
    @DrawableRes iconResId: Int,
    iconDesc: String,
    onClick: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column {
            ContainerAppBar(
                title = containerAppBarTitle,
                iconResId = iconResId,
                iconDesc = iconDesc,
                onClick = onClick
            )
            ScreenContent { content() }
        }
    }
}