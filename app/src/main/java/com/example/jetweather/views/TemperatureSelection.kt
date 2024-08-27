package com.example.jetweather.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.example.jetweather.ui.theme.primaryP10
import com.example.jetweather.ui.theme.primaryP60
import com.example.jetweather.viewmodel.CurrentWeatherViewModel

@Composable
fun TemperatureSelection(viewModel: CurrentWeatherViewModel) {
    val radioOptions = listOf("celsius", "fahrenheit")
    val selectedOption by viewModel.temperatureUnit.collectAsState(initial = radioOptions[0])

    Column(Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { viewModel.saveTemperatureUnit(text) }, // Update the ViewModel when selected
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = primaryP10,
                        unselectedColor = primaryP60
                    )
                )
                Text(
                    text = text.capitalize(Locale.current),
                    style = MaterialTheme.typography.bodyLarge,
                    color = primaryP10,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
