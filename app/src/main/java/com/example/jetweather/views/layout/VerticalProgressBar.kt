package com.example.jetweather.views.layout

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalProgressBar(
    modifier: Modifier = Modifier,
    sectionColors: List<Color>,
    sectionValues: List<String>,
    sectionContents: List<@Composable () -> Unit>,
    barWidth: Dp = 16.dp,
    cornerRadius: Dp = 16.dp
) {
    // Ensure we have exactly 5 sections and contents
    require(sectionColors.size == 5) { "You must provide exactly 5 colors for the sections." }
    require(sectionValues.size == 5) { "You must provide exactly 5 values for the sections." }
    require(sectionContents.size == 5) { "You must provide exactly 5 contents for the sections." }

    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        // Text on the left side
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .padding(end = 8.dp)
        ) {
            sectionValues.forEachIndexed { _, value ->
                Box(
                    modifier = Modifier
                        .weight(1f) // Ensure each label takes up equal space
                        .fillMaxHeight(),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text(
                        text = value,
                        color = Color.White,
                    )
                }
            }
        }

        // Progress bar in the center
        Canvas(
            modifier = Modifier
                .width(barWidth)
                .fillMaxHeight(0.5f)
        ) {
            val sectionHeight = size.height / 5
            val cornerRadiusPx = cornerRadius.toPx()

            sectionColors.forEachIndexed { index, color ->
                val topLeftRadius = if (index == 0) cornerRadiusPx else 0f
                val topRightRadius = if (index == 0) cornerRadiusPx else 0f
                val bottomLeftRadius = if (index == 4) cornerRadiusPx else 0f
                val bottomRightRadius = if (index == 4) cornerRadiusPx else 0f

                val rectPath = Path().apply {
                    addRoundRect(
                        RoundRect(
                            rect = Rect(
                                offset = Offset(0f, index * sectionHeight),
                                size = Size(size.width, sectionHeight)
                            ),
                            topLeft = CornerRadius(topLeftRadius, topLeftRadius),
                            topRight = CornerRadius(topRightRadius, topRightRadius),
                            bottomLeft = CornerRadius(bottomLeftRadius, bottomLeftRadius),
                            bottomRight = CornerRadius(bottomRightRadius, bottomRightRadius)
                        )
                    )
                }

                drawPath(
                    path = rectPath,
                    color = color
                )
            }
        }

        // Customizable content for each section on the right side
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            sectionContents.forEach { content ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(0.5f),
                    contentAlignment = Alignment.TopStart
                ) {
                    content()
                }
            }
        }
    }
}
