package com.example.jetweather.views.horizontalpager

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.jetweather.ui.theme.primaryP40
import com.example.jetweather.ui.theme.primaryP70
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeatherToggleButtonsBar(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    var moved by remember { mutableIntStateOf(0) }
    var buttonSize by remember { mutableStateOf(Size.Zero) }

    val spacing = 8.dp
    val spacingPx = with(LocalDensity.current) { spacing.toPx() }

    val offset by animateIntOffsetAsState(
        targetValue = when (pagerState.currentPage) {
            0 -> IntOffset.Zero
            1 -> IntOffset((buttonSize.width + spacingPx).roundToInt(), 0)
            else -> IntOffset.Zero
        },
        label = "offset"
    )

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(primaryP40, RoundedCornerShape(100.dp))
    ) {
        AnimatedMovingBox(offset = offset, size = buttonSize)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing, Alignment.CenterHorizontally),
        ) {
            WeatherToggleButton(
                text = "Today",
                onClick = {
                    onButtonClick(scope, pagerState, 0) { moved = it }
                },
                modifier = Modifier.weight(1f).onGloballyPositioned { coordinates ->
                    buttonSize = coordinates.size.toSize()
                },
            )
            WeatherToggleButton(
                text = "Weekly",
                onClick = {
                    onButtonClick(scope, pagerState, 1) { moved = it }
                },
                modifier = Modifier.weight(1f).onGloballyPositioned { coordinates ->
                    buttonSize = coordinates.size.toSize()
                },
            )
        }
    }
}

@Composable
fun AnimatedMovingBox(offset: IntOffset, size: Size) {
    Box(
        modifier = Modifier
            .offset { offset }
            .background(primaryP70, RoundedCornerShape(100.dp))
            .width(with(LocalDensity.current) { size.width.toDp() })
            .height(with(LocalDensity.current) { size.height.toDp() })
    )
}

@OptIn(ExperimentalFoundationApi::class)
fun onButtonClick(
    scope: CoroutineScope,
    pagerState: PagerState,
    page: Int,
    updateMovedState: (Int) -> Unit
) {
    scope.launch {
        pagerState.animateScrollToPage(page)
    }
    updateMovedState(pagerState.currentPage)
}

