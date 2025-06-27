package com.marlonmafra.segmentedTab.internal

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.animateBounds
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun LookaheadScope.SelectedSegment(color: Color, shape: Shape) {
    Box(
        modifier = Modifier
            .animateBounds(this)
            .fillMaxWidth()
            .background(color = color, shape = shape)
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
@Preview(showBackground = true)
private fun SelectedSegmentPreview() {
    val segmentedShape = RoundedCornerShape(8.dp)
    val segmentedBackgroundColor = Color.LightGray
    Column(modifier = Modifier
        .padding(PaddingValues(5.dp))
        .height(50.dp)) {
        LookaheadScope {
            Layout(
                content = {
                    SelectedSegment(Color.White, RoundedCornerShape(8.dp))
                },
                modifier = Modifier
                    .background(segmentedBackgroundColor, segmentedShape)
                    .height(50.dp)
                    .padding(4.dp)
            ) { measurables, constraints ->
                val segmentedBoxPlaceable = measurables[0].measure(constraints)

                layout(constraints.maxWidth, constraints.maxHeight) {
                    segmentedBoxPlaceable.placeRelative(x = 0, y = 0)
                }
            }
        }
    }
}